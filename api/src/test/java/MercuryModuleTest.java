import me.adamix.mercury.configuration.api.MercuryArray;
import me.adamix.mercury.configuration.api.MercuryTable;
import me.adamix.mercury.configuration.api.module.ConfigModule;
import me.adamix.mercury.configuration.api.parser.ConfigParsers;
import me.adamix.mercury.configuration.api.parser.ParserRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MercuryModuleTest {

	record TestType(String value) {
	}

    static class TestModule implements ConfigModule {

        @Override
        public @NotNull String name() {
            return "TestModule";
        }

        @Override
        public void register(@NotNull ParserRegistry registry) {
            registry.registerParser(TestType.class, (table, key) -> new TestType(table.getString(key)));
        }
    }

    @Test
    void testModuleParserRegistration() {
        TestModule module = new TestModule();
        ConfigParsers.register(module);

        assertTrue(ConfigParsers.getInstance().hasParser(TestType.class));

        MercuryTable table = new MercuryTable() {
            @Override public @NotNull String name() { return "dummy"; }
            @Override public boolean contains(@NotNull String dottedKey) { return "foo".equals(dottedKey); }
            @Override public @NotNull Object getObject(@NotNull String dottedKey) { return "bar"; }
            @Override public @NotNull String getString(@NotNull String key, @Nullable String def) { return "bar"; }
            @Override public int getInteger(@NotNull String key, int def) { return def; }
            @Override public long getLong(@NotNull String key, long def) { return def; }
            @Override public float getFloat(@NotNull String key, float def) { return def; }
            @Override public double getDouble(@NotNull String key, double def) { return def; }
            @Override public boolean getBoolean(@NotNull String key, boolean def) { return def; }
            @Override public @Nullable MercuryTable getTable(@NotNull String key, @Nullable MercuryTable def) { return def; }
            @Override public @Nullable MercuryArray getArray(@NotNull String key, @Nullable MercuryArray def) { return def; }
            @Override public Set<Map.Entry<String, Object>> dottedEntrySet(boolean includeTables) { return Set.of(); }
            @Override public Set<String> keySet() { return Set.of(); }
            @Override public Set<String> dottedKeySet() { return Set.of(); }
            @Override public Set<String> dottedKeySet(boolean includeTables) { return Set.of(); }
        };

        TestType parsed = ConfigParsers.parse(table, "foo", TestType.class);
	    assertNotNull(parsed);
        assertEquals("bar", parsed.value);
    }
}
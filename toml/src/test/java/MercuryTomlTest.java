import me.adamix.mercury.configuration.api.MercuryArray;
import me.adamix.mercury.configuration.api.MercuryTable;
import me.adamix.mercury.configuration.toml.MercuryTomlArray;
import me.adamix.mercury.configuration.toml.MercuryTomlTable;
import org.junit.jupiter.api.Test;
import org.tomlj.Toml;
import org.tomlj.TomlParseResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


class MercuryTomlTest {
    private static final String SAMPLE_TOML = """
        title = "Mercury Config"
        version = 3
        active = true
        rating = 4.5
        nested = { key1 = "value1", key2 = 42 }
        values = [1, 2, 3, 4]
        nested_array = [{a=1}, {a=2}]
        """;

    private MercuryTable loadTable() {
        TomlParseResult result = Toml.parse(SAMPLE_TOML);
        return new MercuryTomlTable(result, "root");
    }

    @Test
    void testBasicValues() {
        MercuryTable table = loadTable();

        assertEquals("Mercury Config", table.getString("title"));
        assertEquals(3, table.getInteger("version"));
        assertTrue(table.getBoolean("active"));
        assertEquals(4.5, table.getDouble("rating"), 0.001);
    }

    @Test
    void testDefaults() {
        MercuryTable table = loadTable();

        assertEquals("default", table.getString("missing", "default"));
        assertEquals(99, table.getInteger("missing", 99));
        assertFalse(table.getBoolean("missing", false));
        assertEquals(1.5, table.getDouble("missing", 1.5), 0.001);
    }

    @Test
    void testNestedTable() {
        MercuryTable table = loadTable();
        MercuryTable nested = table.getTable("nested");

        assertNotNull(nested);
        assertEquals("value1", nested.getString("key1"));
        assertEquals(42, nested.getInteger("key2"));
        assertNull(table.getTable("missing"));
    }

    @Test
    void testArrayValues() {
        MercuryTable table = loadTable();
        MercuryArray values = table.getArray("values");

        assertNotNull(values);
        assertEquals(4, values.size());
        assertEquals(1, values.getInteger(0));
        assertEquals(4, values.getInteger(3));

        MercuryArray missingArray = table.getArray("missing", new MercuryTomlArray(null, "default"));
        assertNotNull(missingArray);
    }

    @Test
    void testNestedArrayOfTables() {
        MercuryTable table = loadTable();
        MercuryArray nestedArray = table.getArray("nested_array");

        assertEquals(2, nestedArray.size());
        MercuryTable first = nestedArray.getTable(0);
        MercuryTable second = nestedArray.getTable(1);

        assertEquals(1, first.getInteger("a"));
        assertEquals(2, second.getInteger("a"));
    }
}
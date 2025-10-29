package me.adamix.mercury.configuration.api.parser;

import me.adamix.mercury.configuration.api.MercuryTable;
import me.adamix.mercury.configuration.api.module.ConfigModule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Central registry for all {@link ConfigParser} instances in MercuryConfiguration.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * ConfigParsers.register(new JdaConfigModule());
 * EmbedBuilder embed = ConfigParsers.parse(table, "embed_key", EmbedBuilder.class);
 * }</pre>
 */
public class ConfigParsers implements ParserRegistry {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigParsers.class);
	private static final ConfigParsers INSTANCE = new ConfigParsers();

	/**
	 * Returns the singleton instance of the parser registry.
	 *
	 * @return The global ConfigParsers instance.
	 */
	public static ConfigParsers getInstance() {
		return INSTANCE;
	}

	/** Internal map storing parsers by their target type. */
	private final Map<Class<?>, ConfigParser<?>> parserMap = new HashMap<>();

	/**
	 * Registers a parser for the specified type.
	 *
	 * @param clazz  The class type the parser handles.
	 * @param parser The parser instance.
	 * @param <T>    The type handled by the parser.
	 */
	@Override
	public <T> void registerParser(@NotNull Class<T> clazz, @NotNull ConfigParser<T> parser) {
		parserMap.put(clazz, parser);
	}

	/**
	 * Checks whether a parser for the given type exists.
	 *
	 * @param type The class type to check.
	 * @return True if a parser is registered for this type, false otherwise.
	 */
	@Override
	public boolean hasParser(@NotNull Class<?> type) {
		return parserMap.containsKey(type);
	}

	/**
	 * Retrieves the parser registered for a given type.
	 *
	 * @param type The class of the type to parse.
	 * @param <T>  The type parameter.
	 * @return The parser for the type, or null if none is registered.
	 */
	@Override
	public <T> @Nullable ConfigParser<T> getParser(@NotNull Class<T> type) {
		//noinspection unchecked
		return (ConfigParser<T>) parserMap.get(type);
	}

	/**
	 * Registers a module and calls its {@link ConfigModule#register(ParserRegistry)} method.
	 *
	 * @param module The module to register.
	 */
	public static void register(@NotNull ConfigModule module) {
		module.register(ConfigParsers.getInstance());
		LOGGER.info("Module {} has been loaded", module.name());
	}

	/**
	 * Parses a value from a configuration table using a registered parser.
	 *
	 * @param table The configuration table to read from.
	 * @param key   The key in the configuration table.
	 * @param type  The class of the type to parse.
	 * @param <T>   The type parameter.
	 * @return The parsed object.
	 * @throws IllegalStateException If no parser is registered for the given type.
	 */
	public static <T> @Nullable T parse(@NotNull MercuryTable table, @NotNull String key, @NotNull Class<T> type) {
		ConfigParser<T> parser = ConfigParsers.INSTANCE.getParser(type);
		if (parser == null) throw new IllegalStateException("No parser registered for " + type.getName());
		return parser.parse(table, key);
	}
}

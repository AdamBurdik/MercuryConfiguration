package me.adamix.mercury.configuration.api.parser;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a registry that allows modules to register and retrieve custom parsers.
 *
 * <p>Modules implementing {@link me.adamix.mercury.configuration.api.module.ConfigModule}
 * should use this interface to register parsers for their custom types.</p>
 *
 * @see ConfigParsers
 */
public interface ParserRegistry {

	/**
	 * Registers a parser for a specific type.
	 *
	 * @param clazz  The class type the parser handles.
	 * @param parser The parser instance.
	 * @param <T>    The type handled by the parser.
	 */
	<T> void registerParser(@NotNull Class<T> clazz, @NotNull ConfigParser<T> parser);

	/**
	 * Checks whether a parser for the given type is registered.
	 *
	 * @param type The class type to check.
	 * @return True if a parser exists, false otherwise.
	 */
	boolean hasParser(@NotNull Class<?> type);

	/**
	 * Retrieves the parser registered for a given type.
	 *
	 * @param type The class of the type to parse.
	 * @param <T>  The type parameter.
	 * @return The parser for the type, or null if none is registered.
	 */
	<T> @Nullable ConfigParser<T> getParser(@NotNull Class<T> type);
}

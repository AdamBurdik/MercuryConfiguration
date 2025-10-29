package me.adamix.mercury.configuration.api.module;

import me.adamix.mercury.configuration.api.parser.ParserRegistry;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an extension module for MercuryConfiguration.
 * Modules can register additional parsers or behaviors.
 */
public interface ConfigModule {

	/**
	 * The unique name of this module.
	 * <br>
	 * Used for logging or debugging.
	 */
	@NotNull String name();

	/**
	 * Called when the module is loaded.
	 * <br>
	 * Modules should register all of their parsers here.
	 *
	 * @param registry The parser registry used by MercuryConfiguration.
	 */
	void register(@NotNull ParserRegistry registry);
}
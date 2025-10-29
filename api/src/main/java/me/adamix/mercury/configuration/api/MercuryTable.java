package me.adamix.mercury.configuration.api;

import me.adamix.mercury.configuration.api.exception.MissingPropertyException;
import me.adamix.mercury.configuration.api.parser.ConfigParsers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;

/**
 * Represents a configuration section or structured data node in the Mercury configuration system.
 */
public interface MercuryTable {

	@NotNull String name();

	boolean contains(@NotNull String dottedKey);

	@Nullable Object getObject(@NotNull String dottedKey);

	// ------------------ STRING ------------------
	default @Nullable String getString(@NotNull String key) {
		return getString(key, null);
	}

	@Nullable String getString(@NotNull String key, @Nullable String def);

	// ------------------ INTEGER ------------------
	default int getInteger(@NotNull String key) {
		return getInteger(key, 0);
	}

	int getInteger(@NotNull String key, int def);

	// ------------------ LONG ------------------
	default long getLong(@NotNull String key) {
		return getLong(key, 0L);
	}

	long getLong(@NotNull String key, long def);

	// ------------------ FLOAT ------------------
	default float getFloat(@NotNull String key) {
		return getFloat(key, 0f);
	}

	float getFloat(@NotNull String key, float def);

	// ------------------ DOUBLE ------------------
	default double getDouble(@NotNull String key) {
		return getDouble(key, 0d);
	}

	double getDouble(@NotNull String key, double def);

	// ------------------ BOOLEAN ------------------
	default boolean getBoolean(@NotNull String key) {
		return getBoolean(key, false);
	}

	boolean getBoolean(@NotNull String key, boolean def);

	// ------------------ TABLE ------------------
	default @Nullable MercuryTable getTable(@NotNull String key) {
		return getTable(key, null);
	}

	@Nullable MercuryTable getTable(@NotNull String key, @Nullable MercuryTable def);

	// ------------------ ARRAY ------------------
	default @Nullable MercuryArray getArray(@NotNull String key) {
		return getArray(key, null);
	}

	@Nullable MercuryArray getArray(@NotNull String key, @Nullable MercuryArray def);

	// ------------------ META ------------------
	Set<Map.Entry<String, Object>> dottedEntrySet(boolean includeTables);

	Set<String> keySet();
	Set<String> dottedKeySet();
	Set<String> dottedKeySet(boolean includeTables);

	// ------------------ GENERIC GET ------------------

	/**
	 * Retrieves and parses a value using the registered ConfigParser.
	 */
	default <T> @Nullable T get(@NotNull String key, @NotNull Class<T> type) {
		if (!contains(key)) return null;
		return ConfigParsers.parse(this, key, type);
	}

	/**
	 * Retrieves and parses a value with a default fallback.
	 */
	default <T> @NotNull T get(@NotNull String key, @NotNull Class<T> type, @NotNull T def) {
		T value = get(key, type);
		return value != null ? value : def;
	}

	/**
	 * Retrieves and parses a value or throws if missing.
	 */
	default <T> @NotNull T getSafe(@NotNull String key, @NotNull Class<T> type) throws MissingPropertyException {
		T value = get(key, type);
		if (value == null) throw new MissingPropertyException(key, name());
		return value;
	}
}

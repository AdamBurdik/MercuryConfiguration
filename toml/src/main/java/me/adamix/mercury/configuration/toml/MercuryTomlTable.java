package me.adamix.mercury.configuration.toml;

import me.adamix.mercury.configuration.api.MercuryArray;
import me.adamix.mercury.configuration.api.MercuryTable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.tomlj.TomlArray;
import org.tomlj.TomlTable;

import java.util.Map;
import java.util.Set;

/**
 * MercuryTable implementation for TOML tables.
 */
public class MercuryTomlTable implements MercuryTable {

	private final @NotNull TomlTable tomlTable;
	private final @NotNull String name;

	public MercuryTomlTable(@NotNull TomlTable tomlTable, @NotNull String name) {
		this.tomlTable = tomlTable;
		this.name = name;
	}

	@Override
	public @NotNull String name() {
		return name;
	}

	@Override
	public boolean contains(@NotNull String dottedKey) {
		return tomlTable.contains(dottedKey);
	}

	@Override
	public @Nullable Object getObject(@NotNull String dottedKey) {
		return tomlTable.get(dottedKey);
	}

	@Override
	public @Nullable String getString(@NotNull String key, @Nullable String def) {
		String value = tomlTable.getString(key);
		return value != null ? value : def;
	}

	@Override
	public int getInteger(@NotNull String key, int def) {
		Long value = tomlTable.getLong(key);
		return value != null ? value.intValue() : def;
	}

	@Override
	public long getLong(@NotNull String key, long def) {
		Long value = tomlTable.getLong(key);
		return value != null ? value : def;
	}

	@Override
	public float getFloat(@NotNull String key, float def) {
		Double value = tomlTable.getDouble(key);
		return value != null ? value.floatValue() : def;
	}

	@Override
	public double getDouble(@NotNull String key, double def) {
		Double value = tomlTable.getDouble(key);
		return value != null ? value : def;
	}

	@Override
	public boolean getBoolean(@NotNull String key, boolean def) {
		Boolean value = tomlTable.getBoolean(key);
		return value != null ? value : def;
	}

	@Override
	public @Nullable MercuryTable getTable(@NotNull String key, @Nullable MercuryTable def) {
		TomlTable table = tomlTable.getTable(key);
		return table != null ? new MercuryTomlTable(table, name + "." + key) : def;
	}

	@Override
	public @Nullable MercuryArray getArray(@NotNull String key, @Nullable MercuryArray def) {
		TomlArray array = tomlTable.getArray(key);
		return array != null ? new MercuryTomlArray(array, name + "." + key) : def;
	}

	@Override
	public Set<Map.Entry<String, Object>> dottedEntrySet(boolean includeTables) {
		return tomlTable.dottedEntrySet();
	}

	@Override
	public Set<String> keySet() {
		return tomlTable.keySet();
	}

	@Override
	public Set<String> dottedKeySet() {
		return tomlTable.dottedKeySet();
	}

	@Override
	public Set<String> dottedKeySet(boolean includeTables) {
		return tomlTable.dottedKeySet(includeTables);
	}
}

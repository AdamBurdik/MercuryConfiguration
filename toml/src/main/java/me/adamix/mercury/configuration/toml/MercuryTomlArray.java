package me.adamix.mercury.configuration.toml;

import me.adamix.mercury.configuration.api.MercuryArray;
import me.adamix.mercury.configuration.api.MercuryTable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.tomlj.TomlArray;
import org.tomlj.TomlTable;

/**
 * MercuryArray implementation for TOML arrays.
 */
public class MercuryTomlArray implements MercuryArray {

	private final @NotNull TomlArray tomlArray;
	private final @NotNull String name;

	public MercuryTomlArray(@NotNull TomlArray tomlArray, @NotNull String name) {
		this.tomlArray = tomlArray;
		this.name = name;
	}

	@Override
	public @NotNull String name() {
		return name;
	}

	@Override
	public int size() {
		return tomlArray.size();
	}

	@Override
	public boolean isEmpty() {
		return tomlArray.isEmpty();
	}

	@Override
	public boolean contains(int index) {
		return index >= 0 && index < tomlArray.size();
	}

	@Override
	public @Nullable Object getObject(int index) {
		return tomlArray.get(index);
	}

	@Override
	public @Nullable String getString(int index) {
		return tomlArray.getString(index);
	}

	@Override
	public @NotNull String getString(int index, @NotNull String def) {
		String value = getString(index);
		return value != null ? value : def;
	}

	@Override
	public @Nullable Integer getInteger(int index) {
		Long value = getLong(index);
		return value != null ? value.intValue() : null;
	}

	@Override
	public int getInteger(int index, int def) {
		Integer value = getInteger(index);
		return value != null ? value : def;
	}

	@Override
	public @Nullable Long getLong(int index) {
		return tomlArray.getLong(index);
	}

	@Override
	public long getLong(int index, long def) {
		Long value = getLong(index);
		return value != null ? value : def;
	}

	@Override
	public @Nullable Float getFloat(int index) {
		Double value = getDouble(index);
		return value != null ? value.floatValue() : null;
	}

	@Override
	public float getFloat(int index, float def) {
		Float value = getFloat(index);
		return value != null ? value : def;
	}

	@Override
	public @Nullable Double getDouble(int index) {
		return tomlArray.getDouble(index);
	}

	@Override
	public double getDouble(int index, double def) {
		Double value = getDouble(index);
		return value != null ? value : def;
	}

	@Override
	public @Nullable Boolean getBoolean(int index) {
		return tomlArray.getBoolean(index);
	}

	@Override
	public boolean getBoolean(int index, boolean def) {
		Boolean value = getBoolean(index);
		return value != null ? value : def;
	}

	@Override
	public @Nullable MercuryArray getArray(int index) {
		TomlArray array = tomlArray.getArray(index);
		return array != null ? new MercuryTomlArray(array, name + "[" + index + "]") : null;
	}

	@Override
	public @NotNull MercuryArray getArray(int index, @NotNull MercuryArray def) {
		MercuryArray value = getArray(index);
		return value != null ? value : def;
	}

	@Override
	public @Nullable MercuryTable getTable(int index) {
		TomlTable table = tomlArray.getTable(index);
		return table != null ? new MercuryTomlTable(table, name + "[" + index + "]") : null;
	}

	@Override
	public @NotNull MercuryTable getTable(int index, @NotNull MercuryTable def) {
		MercuryTable value = getTable(index);
		return value != null ? value : def;
	}
}

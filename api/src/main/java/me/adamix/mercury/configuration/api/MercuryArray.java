package me.adamix.mercury.configuration.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents an ordered list of values in a configuration source.
 * Provides typed getters with optional default values.
 */
public interface MercuryArray {

	@NotNull String name();

	int size();

	boolean isEmpty();

	boolean contains(int index);

	@Nullable Object getObject(int index);

	@Nullable String getString(int index);
	@NotNull String getString(int index, @NotNull String def);

	@Nullable Integer getInteger(int index);
	int getInteger(int index, int def);

	@Nullable Long getLong(int index);
	long getLong(int index, long def);

	@Nullable Float getFloat(int index);
	float getFloat(int index, float def);

	@Nullable Double getDouble(int index);
	double getDouble(int index, double def);

	@Nullable Boolean getBoolean(int index);
	boolean getBoolean(int index, boolean def);

	@Nullable MercuryArray getArray(int index);
	@NotNull MercuryArray getArray(int index, @NotNull MercuryArray def);

	@Nullable MercuryTable getTable(int index);
	@NotNull MercuryTable getTable(int index, @NotNull MercuryTable def);

	default @NotNull String[] toStringArray() {
		String[] array = new String[size()];
		for (int i = 0; i < size(); i++) array[i] = getString(i);
		return array;
	}

	default @NotNull Integer[] toIntArray() {
		Integer[] array = new Integer[size()];
		for (int i = 0; i < size(); i++) array[i] = getInteger(i);
		return array;
	}

	default @NotNull Long[] toLongArray() {
		Long[] array = new Long[size()];
		for (int i = 0; i < size(); i++) array[i] = getLong(i);
		return array;
	}

	default @NotNull Float[] toFloatArray() {
		Float[] array = new Float[size()];
		for (int i = 0; i < size(); i++) array[i] = getFloat(i);
		return array;
	}

	default @NotNull Double[] toDoubleArray() {
		Double[] array = new Double[size()];
		for (int i = 0; i < size(); i++) array[i] = getDouble(i);
		return array;
	}

	default @NotNull Boolean[] toBooleanArray() {
		Boolean[] array = new Boolean[size()];
		for (int i = 0; i < size(); i++) array[i] = getBoolean(i);
		return array;
	}

	default @NotNull MercuryTable[] toTableArray() {
		MercuryTable[] array = new MercuryTable[size()];
		for (int i = 0; i < size(); i++) array[i] = getTable(i);
		return array;
	}

	default @NotNull MercuryArray[] toArrayArray() {
		MercuryArray[] array = new MercuryArray[size()];
		for (int i = 0; i < size(); i++) array[i] = getArray(i);
		return array;
	}
}

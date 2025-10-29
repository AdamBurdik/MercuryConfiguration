package me.adamix.mercury.configuration.api.parser;

import me.adamix.mercury.configuration.api.MercuryArray;
import me.adamix.mercury.configuration.api.MercuryTable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public interface ConfigParser<T> {
	@Nullable T parse(@NotNull MercuryTable table, @NotNull String key);

	default @Nullable List<T> parseList(@NotNull MercuryTable table, @NotNull String key) {
		MercuryArray array = table.getArray(key);
		if (array == null) return null;

		List<T> result = new ArrayList<>();
		for (int i = 0; i < array.size(); i++) {
			MercuryTable tableChild = array.getTable(i);
			if (tableChild != null) result.add(parse(tableChild, key));
		}
		return result;
	}
}

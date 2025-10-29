package me.adamix.mercury.configuration.modules.jda.parser;

import me.adamix.mercury.configuration.api.MercuryArray;
import me.adamix.mercury.configuration.api.MercuryTable;
import me.adamix.mercury.configuration.api.parser.ConfigParser;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.Color;
import java.time.Instant;

/**
 * Parses a {@link MessageEmbed} from a {@link MercuryTable}.
 * <br>
 * Supports title, description, color, footer, author, thumbnail, image, and fields.
 */
public class EmbedParser implements ConfigParser<MessageEmbed> {

	@Override
	public @Nullable MessageEmbed parse(@NotNull MercuryTable table, @NotNull String key) {
		MercuryTable embed = table.getTable(key);
		if (embed == null) return null;

		EmbedBuilder builder = new EmbedBuilder();
		builder.setTitle(embed.getString("title", null));
		builder.setDescription(embed.getString("description", null));
		builder.setColor(new Color(embed.getInteger("color", 0xFFFFFF)));

		// Footer
		MercuryTable footer = embed.getTable("footer");
		if (footer != null) {
			builder.setFooter(
					footer.getString("text", null),
					footer.getString("iconUrl", null)
			);
		}

		// Author
		MercuryTable author = embed.getTable("author");
		if (author != null) {
			builder.setAuthor(
					author.getString("name", null),
					author.getString("url", null),
					author.getString("iconUrl", null)
			);
		}

		// Image & Thumbnail
		builder.setImage(embed.getString("image", null));
		builder.setThumbnail(embed.getString("thumbnail", null));

		// Timestamp
		String timestamp = embed.getString("timestamp", null);
		if (timestamp != null) {
			try { builder.setTimestamp(Instant.parse(timestamp)); }
			catch (Exception ignored) {}
		}

		// Fields
		MercuryArray fields = embed.getArray("fields");
		if (fields != null) {
			for (int i = 0; i < fields.size(); i++) {
				MercuryTable field = fields.getTable(i);
				if (field != null) {
					builder.addField(
							field.getString("name", ""),
							field.getString("value", ""),
							field.getBoolean("inline", false)
					);
				}
			}
		}

		return builder.build();
	}
}

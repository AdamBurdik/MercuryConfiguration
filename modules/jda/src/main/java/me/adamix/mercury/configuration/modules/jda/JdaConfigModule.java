package me.adamix.mercury.configuration.modules.jda;

import me.adamix.mercury.configuration.api.module.ConfigModule;
import me.adamix.mercury.configuration.api.parser.ParserRegistry;
import me.adamix.mercury.configuration.modules.jda.parser.EmbedParser;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.jetbrains.annotations.NotNull;

public class JdaConfigModule implements ConfigModule {
	@Override
	public @NotNull String name() {
		return "MercuryJda";
	}

	@Override
	public void register(@NotNull ParserRegistry registry) {
		registry.registerParser(MessageEmbed.class, new EmbedParser());
	}
}

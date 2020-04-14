package view;

import java.util.Arrays;

public enum Brightness {
	BRIGHT("bright"),
	DARK("dark");

	private final String brightness;

	Brightness(final String brightness) {
		this.brightness = brightness;
	}

	public Brightness makeSwitch() {
		return Arrays.stream(values())
				.filter(value -> value != this)
				.findFirst()
				.orElseThrow(RuntimeException::new);
	}

	public String getBrightness() {
		return this.brightness;
	}
}

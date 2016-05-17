package org.globaltester.simulator;

import org.globaltester.logging.tags.LogTag;

public class ApduTagImpl extends LogTag implements ApduTag {

	public enum Direction {
		IN, OUT;
	}
	
	public ApduTagImpl(Direction direction) {
		super("APDU - " + direction);
	}

}

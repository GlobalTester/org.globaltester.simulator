package org.globaltester.simulator;

import org.globaltester.logging.tags.LogTag;

public interface LogTags {
	public final LogTag APDU_TAG_IN = new LogTag("APDU in");
	public final LogTag APDU_TAG_OUT = new LogTag("APDU out");
	public final LogTag APDU_TAG_ENC_IN = new LogTag("APDU in, encrypted");
	public final LogTag APDU_TAG_ENC_OUT = new LogTag("APDU out, encrypted");
}

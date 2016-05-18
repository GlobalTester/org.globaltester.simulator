package org.globaltester.simulator;

import org.globaltester.logging.tags.LogTag;

public interface LogTags {
	public final LogTag APDU_TAG_IN = new LogTag("APDU in");
	public final LogTag APDU_TAG_OUT = new LogTag("APDU out");
	public final LogTag APDU_TAG_DEC_IN = new LogTag("APDU in, decrypted");
	public final LogTag APDU_TAG_DEC_OUT = new LogTag("APDU out, decrypted");
}

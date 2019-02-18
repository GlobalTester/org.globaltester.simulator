package org.globaltester.simulator.device;

import java.util.ArrayList;
import java.util.List;

/**
 * Consolidates access too all available {@link SimulatorDeviceConnector}
 * instances and routes commands to the prioritized instance.
 * 
 * 
 */
public class SimulatorDeviceHandler implements SimulatorDeviceConnector {
	
	private static List<SimulatorDeviceConnector> connectors = new ArrayList<>();


	private static SimulatorDeviceConnector getAvailableConnectorByPriority() {
		SimulatorDeviceConnector retVal = null;
		int retValPriority = Integer.MAX_VALUE;
		for (SimulatorDeviceConnector curConnector : connectors) {
			if ((curConnector.getPriority() < retValPriority) && curConnector.isAvailable()) {
				retVal = curConnector;
				retValPriority = retVal.getPriority();
			}
		}
		return retVal;
	}
	public static void registerDeviceConnector(SimulatorDeviceConnector newConnector) {
		if (!connectors.contains(newConnector)) {
			connectors.add(newConnector);
		}
	}
	
	
	/**
	 * This method terminates all remainders of previous connections.
	 */
	private void cleanup() {
		for (SimulatorDeviceConnector curConnector : connectors) {
//TODO			curConnector.cleanup();
		}
	}

	/**
	 * Starts the ISO14443 Simulator as a thread.
	 */
	public void run() {
		cleanup();
		
		SimulatorDeviceConnector curConnector = getAvailableConnectorByPriority();
		if (curConnector != null) {
			curConnector.run();
		}
		
	}

	/**
	 * Stop all available 
	 */
	public void stop() {
		for (SimulatorDeviceConnector curConnector : connectors) {
			curConnector.stop();
		}
		
	}

	@Override
	public boolean isAvailable() {
		SimulatorDeviceConnector curConnector = getAvailableConnectorByPriority();
		return (curConnector != null);
	}


	@Override
	public int getPriority() {
		return Integer.MAX_VALUE;
	}

}

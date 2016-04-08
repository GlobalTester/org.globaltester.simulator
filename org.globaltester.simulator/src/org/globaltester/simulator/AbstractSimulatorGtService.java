package org.globaltester.simulator;

import java.util.Collection;

import org.globaltester.service.AbstractGtService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

import org.globaltester.service.Activator;
import org.globaltester.service.GtServiceListener;

/**
 * Abstract super class that allows providing {@link Simulator} implementations
 * as {@link GtService}
 * <p/>
 * This class handles the automatic notification of registered
 * {@link GtServiceListener}s when the implementation is registered as OSGi
 * service.
 * 
 * @author amay
 *
 */
public abstract class AbstractSimulatorGtService extends AbstractGtService {

	Class<? extends Simulator> t;

	public AbstractSimulatorGtService(Class<? extends Simulator> t) {
		this.t = t;

		ServiceListener serviceListener = new ServiceListener() {
			@Override
			public void serviceChanged(ServiceEvent event) {
				AbstractSimulatorGtService.this.notifyStatusChange(isRunning());
			}
		};
		Activator.getContext().addServiceListener(serviceListener);

		String filter = "(objectclass=" + Simulator.class.getName() + ")";
		try {
			Activator.getContext().addServiceListener(serviceListener, filter);
		} catch (InvalidSyntaxException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isRunning() {
		BundleContext context = Activator.getContext();

		try {
			Collection<ServiceReference<Simulator>> simulatorRefernces = context.getServiceReferences(Simulator.class,
					null);
			for (ServiceReference<Simulator> curRef : simulatorRefernces) {
				if (t.isAssignableFrom(context.getService(curRef).getClass()))
					return true;
			}
		} catch (InvalidSyntaxException e) {
			e.printStackTrace();
		}
		;

		return false;
	}

}

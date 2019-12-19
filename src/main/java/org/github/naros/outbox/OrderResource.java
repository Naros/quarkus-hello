/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.github.naros.outbox;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Chris Cranford
 */
@Path("/order")
public class OrderResource {
    @Inject
    OrderService orderService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/create")
    public String createSimpleOrder() {
        final Order order = new Order();
        order.setData("test");
        orderService.addOrder(order);
        return "Order Created";
    }
}

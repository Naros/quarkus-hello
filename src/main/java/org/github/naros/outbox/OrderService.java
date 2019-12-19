/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.github.naros.outbox;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import io.debezium.quarkus.outbox.ExportedEvent;

/**
 * @author Chris Cranford
 */
@ApplicationScoped
public class OrderService {
    @PersistenceContext
    EntityManager entityManager;

    @Inject
    Event<ExportedEvent> event;

    @Transactional
    public void addOrder(Order order) {
        order = entityManager.merge(order);
        event.fire(OrderCreatedEvent.of(order));
    }
}

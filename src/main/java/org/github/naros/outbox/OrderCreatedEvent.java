/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.github.naros.outbox;

import java.util.Date;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.debezium.quarkus.outbox.ExportedEvent;

/**
 * @author Chris Cranford
 */
public class OrderCreatedEvent implements ExportedEvent {
    private static ObjectMapper mapper = new ObjectMapper();

    private final long id;
    private final JsonNode order;
    private final Long timestamp;

    private OrderCreatedEvent(Long id, JsonNode order) {
        this.id = id;
        this.order = order;
        this.timestamp = (new Date()).getTime();
    }

    public static OrderCreatedEvent of(Order order) {
        ObjectNode asJson = mapper.createObjectNode()
                .put("id", order.getId())
                .put("data", order.getData());

        return new OrderCreatedEvent(order.getId(), asJson);
    }

    @Override
    public String getAggregateId() {
        return String.valueOf(id);
    }

    @Override
    public String getAggregateType() {
        return "Order";
    }

    @Override
    public JsonNode getPayload() {
        return order;
    }

    @Override
    public String getType() {
        return "OrderCreated";
    }

    @Override
    public Long getTimestamp() {
        return timestamp;
    }
}

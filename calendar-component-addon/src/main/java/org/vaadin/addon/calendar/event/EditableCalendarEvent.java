/*
 * Copyright 2000-2016 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.vaadin.addon.calendar.event;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Extension to the basic {@link CalendarEvent
 * CalendarEvent}. This interface provides setters (and thus editing
 * capabilities) for all {@link CalendarEvent
 * CalendarEvent} fields. For descriptions on the fields, refer to the extended
 * interface.
 * </p>
 *
 * <p>
 * This interface is used by some of the basic Calendar event handlers in the
 * <code>org.vaadin.addon.calendar.ui.handler</code> package to determine
 * whether an event can be edited.
 * </p>
 *
 * @since 7.1
 * @author Vaadin Ltd.
 */

public interface EditableCalendarEvent extends CalendarEvent {

    /**
     * Set the visible text in the calendar for the event.
     *
     * @param caption
     *            The text to show in the calendar
     */
    void setCaption(String caption);

    /**
     * Set the description of the event. This is shown in the calendar when
     * hoovering over the event.
     *
     * @param description
     *            The text which describes the event
     */
    void setDescription(String description);

    /**
     * Set the end date of the event. Must be after the start date.
     *
     * @param end
     *            The end date to set
     */
    void setEnd(Date end);

    /**
     * Set the start date for the event. Must be before the end date
     *
     * @param start
     *            The start date of the event
     */
    void setStart(Date start);

    /**
     * Set the style name for the event used for styling the event cells
     *
     * @param styleName
     *            The stylename to use
     *
     */
    void setStyleName(String styleName);

    /**
     * Does the event span the whole day. If so then set this to true
     *
     * @param isAllDay
     *            True if the event spans the whole day. In this case the start
     *            and end times are ignored.
     */
    void setAllDay(boolean isAllDay);

    /**
     * Get a Notifier
     * @return Returns the Notifier for upadates
     */
    EventChangeNotifier getNotifier();

    /**
     * Event to signal that an event has changed.
     */

    public class EventChangeEvent<EVENT extends EditableCalendarEvent> implements Serializable {

        private EVENT source;

        public EventChangeEvent(EVENT source) {
            this.source = source;
        }

        /**
         * @return the CalendarEvent that has changed
         */
        public EVENT getCalendarEvent() {
            return source;
        }
    }

    /**
     * Listener for EventSetChange events.
     */

    public interface EventChangeListener extends Serializable {

        /**
         * Called when an Event has changed.
         */
        public void eventChange(EventChangeEvent eventChangeEvent);
    }

    /**
     * Notifier interface for EventChange events.
     */

    public interface EventChangeNotifier extends Serializable {

        /**
         * Add a listener to listen for EventChangeEvents. These events are
         * fired when a events properties are changed.
         *
         * @param listener
         *            The listener to add
         */
        void addListener(EventChangeListener listener);

        /**
         * Remove a listener from the event provider.
         *
         * @param listener
         *            The listener to remove
         */
        void removeListener(EventChangeListener listener);
    }

}

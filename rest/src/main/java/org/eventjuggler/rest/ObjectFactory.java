/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.eventjuggler.rest;

import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="mailto:sthorger@redhat.com">Stian Thorgersen</a>
 */
class ObjectFactory {

    public static List<Attendance> createAttendance(List<org.eventjuggler.model.RSVP> rsvp) {
        List<Attendance> l = new LinkedList<Attendance>();
        for (org.eventjuggler.model.RSVP r : rsvp) {
            l.add(createAttendance(r));
        }
        return l;
    }

    public static Attendance createAttendance(org.eventjuggler.model.RSVP rsvp) {
        Attendance a = new Attendance();
        a.setResponse(createString(rsvp.getResponse()));
        a.setDescription(rsvp.getUser().getDescription());
        a.setImageId(rsvp.getUser().getImageId());
        a.setLastName(rsvp.getUser().getLastName());
        a.setName(rsvp.getUser().getName());
        return a;
    }

    public static org.eventjuggler.model.Event createEvent(Event event) {
        org.eventjuggler.model.Event e = new org.eventjuggler.model.Event();
        e.setDescription(event.getDescription());
        e.setImageId(event.getImageId());
        e.setTags(createTags(event.getTags()));
        e.setTime(event.getTime());
        e.setTitle(event.getTitle());
        return e;
    }

    public static List<Event> createEvent(List<org.eventjuggler.model.Event> events) {
        List<Event> l = new LinkedList<Event>();
        for (org.eventjuggler.model.Event e : events) {
            l.add(createEvent(e));
        }
        return l;
    }

    public static Event createEvent(org.eventjuggler.model.Event event) {
        Event e = new Event();
        e.setDescription(event.getDescription());
        e.setId(event.getId());
        e.setImageId(event.getImageId());
        e.setTags(createTags(event.getTags()));
        e.setTime(event.getTime());
        e.setTitle(event.getTitle());
        return e;
    }

    public static String createString(Enum<?> e) {
        return e.toString().toLowerCase().replace('_', ' ');
    }

    public static String[] createTags(String tags) {
        if (tags == null) {
            return null;
        }

        String[] t = tags.split(",");
        for (int i = 0; i < t.length; i++) {
            t[i] = t[i].trim();
        }
        return t;
    }

    public static String createTags(String[] tags) {
        if (tags == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tags.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(tags[i]);
        }
        return sb.toString();
    }

}

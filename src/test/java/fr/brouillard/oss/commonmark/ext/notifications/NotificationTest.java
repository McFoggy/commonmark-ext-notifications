/**
 * Copyright (C) 2016 Matthieu Brouillard [http://oss.brouillard.fr/jgitver] (matthieu@brouillard.fr)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.brouillard.oss.commonmark.ext.notifications;


import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class NotificationTest {
    @Test
    public void testSuccessfromString() {
        Notification result = Notification.fromString("v");
        Assert.assertEquals(Notification.SUCCESS, result);
    }

    @Test
    public void testErrorfromString() {
        Notification result = Notification.fromString("x");
        Assert.assertEquals(Notification.ERROR, result);
    }

    @Test
    public void testWarningfromString() {
        Notification result = Notification.fromString("!");
        Assert.assertEquals(Notification.WARNING, result);
    }

    @Test
    public void testInfofromString() {
        Notification result = Notification.fromString("");
        Assert.assertEquals(Notification.INFO, result);
    }

    @Test
    public void testInfoWhenNull() {
        Notification result = Notification.fromString(null);
        Assert.assertEquals(Notification.INFO, result);
    }

    @Test
    public void testInfoWhenEmpty() {
        Notification result = Notification.fromString("    ");
        Assert.assertEquals(Notification.INFO, result);
    }

    @Test
    public void testInfoWhenTabs() {
        Notification result = Notification.fromString("    ");
        Assert.assertEquals(Notification.INFO, result);
    }

    @Test
    public void testFailsWhenUnexpectedValue() {
        Arrays.asList("a", "unexpected").forEach(value -> {
            Assert.assertThrows(IllegalStateException.class, () -> {
                Notification.fromString(value);
            });
        });
    }
}
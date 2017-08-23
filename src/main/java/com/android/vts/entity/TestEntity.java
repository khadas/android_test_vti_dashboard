/*
 * Copyright (c) 2017 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.android.vts.entity;

import com.google.appengine.api.datastore.Entity;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Entity describing test metadata. */
public class TestEntity implements DashboardEntity {
    protected static final Logger logger = Logger.getLogger(TestEntity.class.getName());

    public static final String KIND = "Test";

    public final String testName;

    /**
     * Create a TestEntity object with status metadata.
     *
     * @param testName The name of the test.
     */
    public TestEntity(String testName) {
        this.testName = testName;
    }

    @Override
    public Entity toEntity() {
        Entity testEntity = new Entity(KIND, this.testName);
        return testEntity;
    }

    /**
     * Convert an Entity object to a TestEntity.
     *
     * @param e The entity to process.
     * @return TestEntity object with the properties from e processed, or null if incompatible.
     */
    @SuppressWarnings("unchecked")
    public static TestEntity fromEntity(Entity e) {
        if (!e.getKind().equals(KIND) || e.getKey().getName() == null) {
            logger.log(Level.WARNING, "Missing test attributes in entity: " + e.toString());
            return null;
        }
        String testName = e.getKey().getName();
        return new TestEntity(testName);
    }
}

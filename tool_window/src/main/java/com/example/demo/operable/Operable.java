/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.example.demo.operable;

public interface Operable {

    Status getStatus();

    void start();

    void stop();

    void restart();

    void setStatus(Status status);

    String getName();

    public static enum Status {
        STARTED, STOPPED, UPDATING
    }
}

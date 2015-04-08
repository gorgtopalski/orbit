/*
Copyright (C) 2015 Electronic Arts Inc.  All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions
are met:

1.  Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
2.  Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.
3.  Neither the name of Electronic Arts, Inc. ("EA") nor the names of
    its contributors may be used to endorse or promote products derived
    from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY ELECTRONIC ARTS AND ITS CONTRIBUTORS "AS IS" AND ANY
EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL ELECTRONIC ARTS OR ITS CONTRIBUTORS BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package com.ea.orbit.actors;

import com.ea.orbit.actors.runtime.ReferenceFactory;
import com.ea.orbit.concurrent.Task;

/**
 * Interface marker for orbit actors.
 * <p>
 * <b>Example:</b>
 * <pre>{@code
 * public interface IHello extends IActor
 * {
 *      Task<String> sayHello();
 * }
 * <p/>
 * public class Hello extends OrbitActor implements IHello
 * {
 *     public Task<String> sayHello() {
 *         return Task.fromValue("hello!");
 *     }
 * }
 * }</pre>
 * </p>
 * <p>
 * The presence of the IActor interface instructs the java compiler to
 * generate a reference factory class for that interface.
 * </p><p>
 * Application code will never touch actor instances directly. It should rather
 * obtain references:
 * <pre>{@code
 *  IHello helloActor = HelloFactory.createReference("001");
 * }</pre>
 * Where HelloFactory was automatically created by the framework.
 * </p>
 */
public interface IActor
{
    /**
     * Gets a reference to an actor.
     *
     * @param iActor the actor interface
     * @param id     the actor id
     * @param <T>    the interface type
     * @return an actor reference
     */
    public static <T extends IActor> T getReference(Class<T> iActor, String id)
    {
        return ReferenceFactory.ref(iActor, id);
    }

    /**
     * Gets a reference to an actor that has the {@literal@}NoIdentity annotation.
     *
     * @param iActor the actor interface
     * @param <T>    the interface type
     * @return an actor reference
     */
    static <T extends IActor> T getReference(Class<T> iActor)
    {
        return ReferenceFactory.ref(iActor);
    }
}

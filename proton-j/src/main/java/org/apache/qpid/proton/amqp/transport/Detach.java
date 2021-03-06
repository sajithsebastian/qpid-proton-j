
/*
*
* Licensed to the Apache Software Foundation (ASF) under one
* or more contributor license agreements.  See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership.  The ASF licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License.  You may obtain a copy of the License at
*
*   http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*
*/


package org.apache.qpid.proton.amqp.transport;

import org.apache.qpid.proton.amqp.Binary;
import org.apache.qpid.proton.amqp.UnsignedInteger;

public final class Detach implements FrameBody
{
    private UnsignedInteger _handle;
    private boolean _closed;
    private ErrorCondition _error;

    public Detach() {}

    public Detach(Detach other)
    {
        this._handle = other._handle;
        this._closed = other._closed;
        if (other._error != null)
        {
            this._error = new ErrorCondition();
            this._error.copyFrom(other._error);
        }
    }

    public UnsignedInteger getHandle()
    {
        return _handle;
    }

    public void setHandle(UnsignedInteger handle)
    {
        if( handle == null )
        {
            throw new NullPointerException("the handle field is mandatory");
        }

        _handle = handle;
    }

    public boolean getClosed()
    {
        return _closed;
    }

    public void setClosed(boolean closed)
    {
        _closed = closed;
    }

    public ErrorCondition getError()
    {
        return _error;
    }

    public void setError(ErrorCondition error)
    {
        _error = error;
    }

    @Override
    public <E> void invoke(FrameBodyHandler<E> handler, Binary payload, E context)
    {
        handler.handleDetach(this, payload, context);
    }

    @Override
    public String toString()
    {
        return "Detach{" +
               "handle=" + _handle +
               ", closed=" + _closed +
               ", error=" + _error +
               '}';
    }

    @Override
    public Detach copy()
    {
        return new Detach(this);
    }
}

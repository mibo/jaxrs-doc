/*
 * Copyright (C) 2015 Sebastian Daschner, sebastian-daschner.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.mibo.jaxrsdoc.analysis.classes.testclasses.resource.response;

import com.github.mibo.jaxrsdoc.model.elements.HttpResponse;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Set;

public class TestClass54 {

    @javax.ws.rs.GET public Response method(final String id) {
        final Response.ResponseBuilder builder = Response.status(200);
        final InnerTestClass innerTestClass = new InnerTestClass(builder);
        System.out.println(innerTestClass);
        return builder.build();
    }

    public static Set<HttpResponse> getResult() {
        final HttpResponse result = new HttpResponse();

        result.getStatuses().add(200);
        // TODO un-comment, relevant instruction reducer reduces all instructions of InnerTestClass due to missing return
        // -> needs to take care of parameter as well
//        result.getHeaders().add("X-Info");

        return Collections.singleton(result);
    }

    private class InnerTestClass {

        public InnerTestClass(final Response.ResponseBuilder builder) {
            builder.header("X-Info", "Test");
        }

    }

}

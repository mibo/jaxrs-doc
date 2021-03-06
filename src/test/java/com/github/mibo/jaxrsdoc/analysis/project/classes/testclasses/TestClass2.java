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

package com.github.mibo.jaxrsdoc.analysis.project.classes.testclasses;

import com.github.mibo.jaxrsdoc.model.Types;
import com.github.mibo.jaxrsdoc.model.rest.HttpMethod;
import com.github.mibo.jaxrsdoc.model.results.ClassResult;
import com.github.mibo.jaxrsdoc.model.results.MethodResult;
import com.github.mibo.jaxrsdoc.builder.ClassResultBuilder;
import com.github.mibo.jaxrsdoc.builder.HttpResponseBuilder;
import com.github.mibo.jaxrsdoc.builder.MethodResultBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("test")
@Produces(MediaType.APPLICATION_JSON)
public class TestClass2 {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public int method() {
        int status = 200;
        return status;
    }

    @POST
    public Response post(final String string) {
        return Response.accepted().build();
    }

    public static ClassResult getResult() {
        final MethodResult firstMethod = MethodResultBuilder.withResponses(HttpResponseBuilder.newBuilder().andEntityTypes(Types.PRIMITIVE_INT)
                .andContentTypes("text/html").build())
                .andResponseMediaTypes("text/html").andMethod(HttpMethod.GET).build();
        final MethodResult secondMethod = MethodResultBuilder.withResponses(HttpResponseBuilder.withStatues(202).build())
                .andMethod(HttpMethod.POST).andRequestBodyType(Types.STRING).build();
        return ClassResultBuilder.withResourcePath("test").andResponseMediaTypes("application/json").andMethods(firstMethod, secondMethod).build();
    }

}

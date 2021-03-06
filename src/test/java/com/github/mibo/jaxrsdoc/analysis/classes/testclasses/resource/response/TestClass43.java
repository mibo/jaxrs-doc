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

import com.github.mibo.jaxrsdoc.model.Types;
import com.github.mibo.jaxrsdoc.model.elements.Element;
import com.github.mibo.jaxrsdoc.model.elements.HttpResponse;
import com.github.mibo.jaxrsdoc.model.elements.JsonArray;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class TestClass43 {

    private List<Object> tasks;

    @javax.ws.rs.GET
    public Response method() {
        return Response.ok(buildJsonArray()).build();
    }

    public javax.json.JsonArray buildJsonArray() {
        final JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        tasks.stream().map(Object::toString).forEach(arrayBuilder::add);
        return arrayBuilder.build();
    }

    public static Set<HttpResponse> getResult() {
        final HttpResponse result = new HttpResponse();

        result.getStatuses().add(200);
        result.getEntityTypes().add(Types.JSON_ARRAY);

        final JsonArray jsonArray = new JsonArray();
        jsonArray.getElements().add(new Element(Types.STRING));

        result.getInlineEntities().add(jsonArray);

        return Collections.singleton(result);
    }

}

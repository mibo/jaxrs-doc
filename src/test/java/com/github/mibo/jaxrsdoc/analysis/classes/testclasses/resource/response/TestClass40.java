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
import com.github.mibo.jaxrsdoc.model.elements.JsonObject;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public class TestClass40 {

    @javax.ws.rs.GET
    public Response method() {
        BiFunction<JsonObjectBuilder, String, JsonObjectBuilder> function = JsonObjectBuilder::addNull;
        BinaryOperator<JsonArrayBuilder> anotherFunction = JsonArrayBuilder::add;

        JsonObjectBuilder builder = Json.createObjectBuilder();
        function.apply(builder, "test");

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder anotherArrayBuilder = Json.createArrayBuilder();

        arrayBuilder.add("test1");
        anotherArrayBuilder.add("test2");
        anotherFunction.apply(arrayBuilder, anotherArrayBuilder);

        builder.add("array", arrayBuilder);

        return Response.ok(builder.build()).build();
    }


    public static Set<HttpResponse> getResult() {
        final HttpResponse result = new HttpResponse();

        result.getStatuses().add(200);
        result.getEntityTypes().add(Types.JSON_OBJECT);

        final JsonArray jsonArray = new JsonArray();
        jsonArray.getElements().add(new Element(Types.STRING, "test1"));

        final JsonArray nestedArray = new JsonArray();
        nestedArray.getElements().add(new Element(Types.STRING, "test2"));
        jsonArray.getElements().add(new Element(Types.JSON_ARRAY, nestedArray));

        JsonObject jsonObject = new JsonObject();
        jsonObject.getStructure().put("test", new Element(Types.OBJECT, null));
        jsonObject.getStructure().put("array", new Element(Types.JSON_ARRAY, jsonArray));

        result.getInlineEntities().add(jsonObject);

        return Collections.singleton(result);
    }

}

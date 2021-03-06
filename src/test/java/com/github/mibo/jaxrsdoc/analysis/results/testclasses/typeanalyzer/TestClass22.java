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

package com.github.mibo.jaxrsdoc.analysis.results.testclasses.typeanalyzer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.mibo.jaxrsdoc.model.Types;
import com.github.mibo.jaxrsdoc.model.rest.TypeIdentifier;
import com.github.mibo.jaxrsdoc.model.rest.TypeRepresentation;
import com.github.mibo.jaxrsdoc.analysis.results.TypeUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Test @JsonIgnore on primitive field
 */
public class TestClass22 {

    private static String PRIVATE_FIELD;
    public static String PUBLIC_FIELD;
    private String privateField;
    protected String protectedField;
    @JsonIgnore
    public String publicField;

    public String getTest() {
        return null;
    }

    public int getInt() {
        return 0;
    }

    public static String getStaticString() {
        return null;
    }

    public String string() {
        return null;
    }

    public static Set<TypeRepresentation> expectedTypeRepresentations() {
        final Map<String, TypeIdentifier> properties = new HashMap<>();


        // properties.put("publicField", TypeUtils.STRING_IDENTIFIER); //ignored by @JsonIgnore
        properties.put("test", TypeUtils.STRING_IDENTIFIER);
        properties.put("int", TypeIdentifier.ofType(Types.PRIMITIVE_INT));

        return Collections.singleton(TypeRepresentation.ofConcrete(expectedIdentifier(), properties));
    }

    public static TypeIdentifier expectedIdentifier() {
        return TypeIdentifier.ofType("Lcom/github/mibo/jaxrsdoc/analysis/results/testclasses/typeanalyzer/TestClass22;");
    }

}

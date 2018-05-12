package com.github.mibo.jaxrsdoc.backend.plaintext;

import com.github.mibo.jaxrsdoc.analysis.results.TypeUtils;
import com.github.mibo.jaxrsdoc.backend.Backend;
import com.github.mibo.jaxrsdoc.backend.StringBackend;
import com.github.mibo.jaxrsdoc.builder.ResponseBuilder;
import com.github.mibo.jaxrsdoc.model.Types;
import com.github.mibo.jaxrsdoc.model.rest.*;
import com.github.mibo.jaxrsdoc.builder.ResourceMethodBuilder;
import com.github.mibo.jaxrsdoc.builder.ResourcesBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static java.util.Collections.singletonMap;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PlainTextBackendTest {

    private final Backend cut;
    private final Resources resources;
    private final String expectedOutput;

    public PlainTextBackendTest(final Resources resources, final String expectedOutput) {
        cut = new PlainTextBackend();
        this.resources = resources;
        this.expectedOutput = expectedOutput;
    }

    @Test
    public void test() {
        final Project project = new Project("project name", "1.0", resources);
        cut.configure(singletonMap(StringBackend.INLINE_PRETTIFY, "false"));
        final String actualOutput = new String(cut.render(project));
        assertEquals(expectedOutput, actualOutput);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        final Collection<Object[]> data = new LinkedList<>();
        final TypeIdentifier stringIdentifier = TypeIdentifier.ofType(Types.STRING);
        final TypeIdentifier intIdentifier = TypeIdentifier.ofType(Types.PRIMITIVE_INT);

        TypeIdentifier identifier;
        Map<String, TypeIdentifier> properties = new HashMap<>();

        add(data, ResourcesBuilder.withBase("rest").andResource("res1", ResourceMethodBuilder.withMethod(HttpMethod.GET)
                        .andResponse(200, ResponseBuilder.withResponseBody(TypeIdentifier.ofType(Types.STRING)).andHeaders("Location").build()).build()).build(),
                "REST resources of project name:\n" +
                        "1.0\n" +
                        "\n" +
                        "GET rest/res1:\n" +
                        " Request:\n" +
                        "  No body\n" +
                        "\n" +
                        " Response:\n" +
                        "  Content-Type: */*\n" +
                        "  Status Codes: 200\n" +
                        "   Header: Location\n" +
                        "   Response Body: java.lang.String\n\n\n");

        identifier = TypeIdentifier.ofDynamic();
        properties.put("key", stringIdentifier);
        properties.put("another", intIdentifier);
        add(data, ResourcesBuilder.withBase("rest")
                        .andTypeRepresentation(identifier, TypeRepresentation.ofConcrete(identifier, properties))
                        .andResource("res2", ResourceMethodBuilder.withMethod(HttpMethod.GET)
                                .andResponse(200, ResponseBuilder.withResponseBody(identifier).build()).build()).build(),
                "REST resources of project name:\n" +
                        "1.0\n" +
                        "\n" +
                        "GET rest/res2:\n" +
                        " Request:\n" +
                        "  No body\n" +
                        "\n" +
                        " Response:\n" +
                        "  Content-Type: */*\n" +
                        "  Status Codes: 200\n" +
                        "   Response Body: javax.json.Json\n" +
                        "    {\"another\":0,\"key\":\"string\"}\n\n\n");

        identifier = TypeIdentifier.ofDynamic();
        properties = new HashMap<>();
        properties.put("key", stringIdentifier);
        properties.put("another", intIdentifier);
        add(data, ResourcesBuilder.withBase("rest")
                        .andTypeRepresentation(identifier, TypeRepresentation.ofCollection(identifier, TypeRepresentation.ofConcrete(TypeIdentifier.ofDynamic(), properties)))
                        .andResource("res3", ResourceMethodBuilder.withMethod(HttpMethod.GET)
                                .andResponse(200, ResponseBuilder.withResponseBody(identifier).build()).build()).build(),
                "REST resources of project name:\n" +
                        "1.0\n" +
                        "\n" +
                        "GET rest/res3:\n" +
                        " Request:\n" +
                        "  No body\n" +
                        "\n" +
                        " Response:\n" +
                        "  Content-Type: */*\n" +
                        "  Status Codes: 200\n" +
                        "   Response Body: javax.json.Json\n" +
                        "    [{\"another\":0,\"key\":\"string\"}]\n\n\n");

        identifier = TypeIdentifier.ofDynamic();
        add(data, ResourcesBuilder.withBase("rest")
                        .andTypeRepresentation(identifier, TypeRepresentation.ofCollection(identifier, TypeRepresentation.ofConcrete(stringIdentifier)))
                        .andResource("res4", ResourceMethodBuilder.withMethod(HttpMethod.GET)
                                .andResponse(200, ResponseBuilder.withResponseBody(identifier).build()).build()).build(),
                "REST resources of project name:\n" +
                        "1.0\n" +
                        "\n" +
                        "GET rest/res4:\n" +
                        " Request:\n" +
                        "  No body\n" +
                        "\n" +
                        " Response:\n" +
                        "  Content-Type: */*\n" +
                        "  Status Codes: 200\n" +
                        "   Response Body: javax.json.Json\n" +
                        "    [\"string\"]\n\n\n");

        identifier = TypeIdentifier.ofDynamic();
        properties = new HashMap<>();
        properties.put("key", stringIdentifier);
        add(data, ResourcesBuilder.withBase("rest")
                        .andTypeRepresentation(identifier, TypeRepresentation.ofCollection(identifier, TypeRepresentation.ofConcrete(TypeIdentifier.ofDynamic(), properties)))
                        .andResource("res5", ResourceMethodBuilder.withMethod(HttpMethod.GET)
                                .andResponse(200, ResponseBuilder.withResponseBody(identifier).build()).build()).build(),
                "REST resources of project name:\n" +
                        "1.0\n" +
                        "\n" +
                        "GET rest/res5:\n" +
                        " Request:\n" +
                        "  No body\n" +
                        "\n" +
                        " Response:\n" +
                        "  Content-Type: */*\n" +
                        "  Status Codes: 200\n" +
                        "   Response Body: javax.json.Json\n" +
                        "    [{\"key\":\"string\"}]\n\n\n");

        properties = new HashMap<>();
        properties.put("name", stringIdentifier);
        properties.put("value", intIdentifier);
        add(data, ResourcesBuilder.withBase("rest")
                        .andTypeRepresentation(TypeUtils.MODEL_IDENTIFIER, TypeRepresentation.ofConcrete(TypeUtils.MODEL_IDENTIFIER, properties))
                        .andResource("res6", ResourceMethodBuilder.withMethod(HttpMethod.GET)
                                .andResponse(200, ResponseBuilder.withResponseBody(TypeUtils.MODEL_IDENTIFIER).build()).build()).build(),
                "REST resources of project name:\n" +
                        "1.0\n" +
                        "\n" +
                        "GET rest/res6:\n" +
                        " Request:\n" +
                        "  No body\n" +
                        "\n" +
                        " Response:\n" +
                        "  Content-Type: */*\n" +
                        "  Status Codes: 200\n" +
                        "   Response Body: com.github.mibo.test.Model\n" +
                        "    {\"name\":\"string\",\"value\":0}\n\n\n");

        identifier = TypeIdentifier.ofType("Ljava/util/List<Lcom/github/mibo/test/Model;>;");
        properties = new HashMap<>();
        properties.put("name", stringIdentifier);
        properties.put("value", intIdentifier);
        add(data, ResourcesBuilder.withBase("rest")
                        .andTypeRepresentation(identifier, TypeRepresentation.ofCollection(identifier, TypeRepresentation.ofConcrete(TypeUtils.MODEL_IDENTIFIER, properties)))
                        .andResource("res7", ResourceMethodBuilder.withMethod(HttpMethod.GET)
                                .andResponse(200, ResponseBuilder.withResponseBody(identifier).build()).build()).build(),
                "REST resources of project name:\n" +
                        "1.0\n" +
                        "\n" +
                        "GET rest/res7:\n" +
                        " Request:\n" +
                        "  No body\n" +
                        "\n" +
                        " Response:\n" +
                        "  Content-Type: */*\n" +
                        "  Status Codes: 200\n" +
                        "   Response Body: Collection of com.github.mibo.test.Model\n" +
                        "    [{\"name\":\"string\",\"value\":0}]\n\n\n");

        add(data, ResourcesBuilder.withBase("rest")
                        .andTypeRepresentation(identifier, TypeRepresentation.ofCollection(identifier, TypeRepresentation.ofConcrete(TypeUtils.MODEL_IDENTIFIER, properties)))
                        .andResource("res8", ResourceMethodBuilder.withMethod(HttpMethod.POST).andRequestBodyType(identifier).andFormParam("form", TypeUtils.MODEL_IDENTIFIER.getType())
                                .andAcceptMediaTypes("application/json").andResponse(201, ResponseBuilder.newBuilder().andHeaders("Location").build()).build()).build(),
                "REST resources of project name:\n" +
                        "1.0\n" +
                        "\n" +
                        "POST rest/res8:\n" +
                        " Request:\n" +
                        "  Content-Type: application/json\n" +
                        "  Request Body: Collection of com.github.mibo.test.Model\n" +
                        "   [{\"name\":\"string\",\"value\":0}]\n" +
                        "  Form Param: form, com.github.mibo.test.Model\n" +
                        "\n" +
                        " Response:\n" +
                        "  Content-Type: */*\n" +
                        "  Status Codes: 201\n" +
                        "   Header: Location\n\n\n");

        add(data, ResourcesBuilder.withBase("rest")
                        .andTypeRepresentation(identifier, TypeRepresentation.ofCollection(identifier, TypeRepresentation.ofConcrete(TypeUtils.MODEL_IDENTIFIER, properties)))
                        .andResource("res9", ResourceMethodBuilder.withMethod(HttpMethod.POST).andRequestBodyType(identifier).andQueryParam("query", Types.PRIMITIVE_INT)
                                .andAcceptMediaTypes("application/json").andResponse(201, ResponseBuilder.newBuilder().andHeaders("Location").build()).build())
                        .andResource("res10", ResourceMethodBuilder.withMethod(HttpMethod.GET).andResponse(200, ResponseBuilder.newBuilder().build()).build()).build(),
                "REST resources of project name:\n" +
                        "1.0\n" +
                        "\n" +
                        "GET rest/res10:\n" +
                        " Request:\n" +
                        "  No body\n" +
                        "\n" +
                        " Response:\n" +
                        "  Content-Type: */*\n" +
                        "  Status Codes: 200\n\n\n" +
                        "POST rest/res9:\n" +
                        " Request:\n" +
                        "  Content-Type: application/json\n" +
                        "  Request Body: Collection of com.github.mibo.test.Model\n" +
                        "   [{\"name\":\"string\",\"value\":0}]\n" +
                        "  Query Param: query, int\n" +
                        "\n" +
                        " Response:\n" +
                        "  Content-Type: */*\n" +
                        "  Status Codes: 201\n" +
                        "   Header: Location\n\n\n");
        add(data, ResourcesBuilder.withBase("rest")
            .andResource("res19", ResourceMethodBuilder.withMethod(HttpMethod.GET).andDeprecated(true)
                .andResponse(200, ResponseBuilder.withResponseBody(TypeIdentifier.ofType(Types.STRING)).andHeaders("Location").build()).build()).build(),
            "REST resources of project name:\n" +
                    "1.0\n" +
                    "\n" +
                    "GET rest/res19:\n" +
                    " Deprecated\n" +
                    " Request:\n" +
                    "  No body\n" +
                    "\n" +
                    " Response:\n" +
                    "  Content-Type: */*\n" +
                    "  Status Codes: 200\n" +
                    "   Header: Location\n" +
                    "   Response Body: java.lang.String\n\n\n");
        return data;
    }

    public static void add(final Collection<Object[]> data, final Resources resources, final String output) {
        final Object[] objects = new Object[2];
        objects[0] = resources;
        objects[1] = output;
        data.add(objects);
    }

}
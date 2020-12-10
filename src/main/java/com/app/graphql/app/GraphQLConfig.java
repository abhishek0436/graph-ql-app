package com.app.graphql.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.app.graphql.app.gql.util.DateUtil;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.GraphQLException;
import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;
import graphql.servlet.GraphQLErrorHandler;

@Configuration
public class GraphQLConfig {

    @Bean
    public GraphQLErrorHandler errorHandler() {
        return new GraphQLErrorHandler() {
            @Override
            public List<GraphQLError> processErrors(List<GraphQLError> errors) {
                List<GraphQLError> clientErrors = errors.stream().filter(this::isClientError).collect(Collectors.toList());

                List<GraphQLError> serverErrors = errors.stream().filter(e -> !isClientError(e)).map(GraphQLErrorAdapter::new).collect(Collectors.toList());

                List<GraphQLError> e = new ArrayList<>();
                e.addAll(clientErrors);
                e.addAll(serverErrors);
                return e;
            }

            protected boolean isClientError(GraphQLError error) {
                return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable || error instanceof GraphQLException);
            }
        };
    }

    @Bean
    public GraphQLScalarType dateScalar() {
        return GraphQLScalarType.newScalar().name("Date").coercing(new Coercing<Date, String>() {
            @Override
            public String serialize(final Object dataFetcherResult) {
                if (dataFetcherResult instanceof Date) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    return simpleDateFormat.format(dataFetcherResult);
                } else {
                    throw new CoercingSerializeException("Expected a LocalDate object.");
                }
            }

            @Override
            public Date parseValue(final Object input) {
                try {
                    if (input instanceof String) {
                        try {
                            return DateUtil.parseDate((String) input);
                        } catch (ParseException e) {
                            throw new CoercingSerializeException("Expected Date with pattern YYYY-MM-DD");
                        }
                    }
                    throw new CoercingParseLiteralException("Expected a String");
                } catch (DateTimeParseException e) {
                    throw new CoercingParseValueException(String.format("Not a valid date: '%s'.", input), e);
                }
            }

            @Override
            public Date parseLiteral(final Object input) {
                if (input instanceof StringValue) {
                    try {
                        try {
                            return DateUtil.parseDate((String) input);
                        } catch (ParseException e) {
                            throw new CoercingSerializeException("Expected Date with pattern YYYY-MM-DD");
                        }
                    } catch (DateTimeParseException e) {
                        throw new CoercingParseLiteralException(e);
                    }
                } else {
                    throw new CoercingParseLiteralException("Expected a StringValue.");
                }
            }
        }).build();
    }

}

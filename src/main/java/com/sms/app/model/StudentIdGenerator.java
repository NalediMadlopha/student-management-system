package com.sms.app.model;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.springframework.data.mapping.MappingException;

import java.util.Properties;
import java.util.stream.Stream;

class StudentIdGenerator implements IdentifierGenerator {

    private static final String VALUE_PREFIX_DEFAULT = "";
    private static final String NUMBER_FORMAT_DEFAULT = "%d";
    public static final String VALUE_PREFIX_PARAMETER = "valuePrefix";
    public static final String NUMBER_FORMAT_PARAMETER = "numberFormat";

    private String valuePrefix;
    private String numberFormat;

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        valuePrefix = ConfigurationHelper.getString(VALUE_PREFIX_PARAMETER, params, VALUE_PREFIX_DEFAULT);
        numberFormat = ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER, params, NUMBER_FORMAT_DEFAULT);
    }

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String query = String.format("select %s from %s", session.getEntityPersister(object.getClass().getName(), object)
                        .getIdentifierPropertyName(),
                object.getClass().getSimpleName());

        Stream<String> ids = session.createQuery(query, String.class).stream();
        long max = ids
                .map(o -> o.replace(valuePrefix, ""))
                .mapToLong(Long::parseLong)
                .max()
                .orElse(0L);

        return valuePrefix + String.format(numberFormat, (max + 1));
    }

}

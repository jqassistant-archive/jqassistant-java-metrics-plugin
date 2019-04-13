package org.jqassistant.contrib.plugin.javametrics;

import com.buschmais.jqassistant.plugin.java.test.AbstractJavaPluginIT;
import com.buschmais.xo.api.Query;

public abstract class AbstractOoMetricsOfPackageIT extends AbstractJavaPluginIT {

    protected <T> T retrieveSingleValue(String query, Class<T> t) {
        Query.Result.CompositeRowObject singleResult = store.executeQuery(query).getSingleResult();
        return (T) singleResult.get(singleResult.getColumns().get(0), t);
    }

}

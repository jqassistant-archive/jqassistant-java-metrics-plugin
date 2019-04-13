package org.jqassistant.contrib.plugin.javametrics;

import static org.junit.Assert.assertEquals;

import com.buschmais.jqassistant.core.analysis.api.Result;
import com.buschmais.jqassistant.core.analysis.api.rule.Concept;
import com.buschmais.jqassistant.core.analysis.api.rule.Severity;
import com.buschmais.jqassistant.plugin.java.test.AbstractJavaPluginIT;
import com.buschmais.xo.api.Query;
import org.junit.Test;

public class MyFirstIT extends AbstractJavaPluginIT {

    private static final String CONCEPT = "ood-metrics:NormalizedDistance";

    @Test
    public void measureClass() throws Exception {
        // arrange
        scanClassPathDirectory(getClassesDirectory(MyFirstIT.class));

        // act
        Result<Concept> result = applyConcept(CONCEPT);

        // assert
        assertEquals(Result.Status.SUCCESS, result.getStatus());
        assertEquals(Severity.MINOR, result.getSeverity());

        store.beginTransaction();
        assertEquals(0.5, retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.set\"}) RETURN t.abstractness", "t.abstractness", Double.class), 0.001);
        store.commitTransaction();
    }

    private <T> T retrieveSingleValue(String query, String column, Class<T> t) {
        Query.Result.CompositeRowObject singleResult = store.executeQuery(query).getSingleResult();
        return (T) singleResult.get(column, t);
    }

}

package org.jqassistant.contrib.plugin.javametrics.oodmetrics;

import static org.junit.Assert.assertEquals;

import com.buschmais.jqassistant.core.report.api.model.Result;
import com.buschmais.jqassistant.core.rule.api.model.Concept;
import com.buschmais.jqassistant.core.rule.api.model.Severity;
import org.jqassistant.contrib.plugin.javametrics.AbstractMetricsIT;
import org.junit.Test;

public class NormalizedDistanceIT extends AbstractMetricsIT {

    @Test
    public void whenApplyDistance_expectValue() throws Exception {
        // arrange
        scanClassPathDirectory(getClassesDirectory(NormalizedDistanceIT.class));

        // act
        Result<Concept> result = applyConcept("ood-metrics:Distance");

        // assert
        assertEquals(Result.Status.SUCCESS, result.getStatus());
        assertEquals(Severity.MINOR, result.getSeverity());

        store.beginTransaction();
        assertEquals(Double.valueOf(0.177), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.a\"}) RETURN t.distance", Double.class), 0.001);
        assertEquals(Double.valueOf(0.000), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.b\"}) RETURN t.distance", Double.class), 0.001);
        assertEquals(Double.valueOf(0.177), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.c\"}) RETURN t.distance", Double.class), 0.001);
        assertEquals(Double.valueOf(0.707), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.d\"}) RETURN t.distance", Double.class), 0.001);
        assertEquals(Double.valueOf(0.707), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.e\"}) RETURN t.distance", Double.class), 0.001);
        store.commitTransaction();
    }

    @Test
    public void whenApplyNormalizedDistance_expectValue() throws Exception {
        // arrange
        scanClassPathDirectory(getClassesDirectory(NormalizedDistanceIT.class));

        // act
        Result<Concept> result = applyConcept("ood-metrics:NormalizedDistance");

        // assert
        assertEquals(Result.Status.SUCCESS, result.getStatus());
        assertEquals(Severity.MINOR, result.getSeverity());

        store.beginTransaction();
        assertEquals(Double.valueOf(0.25), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.a\"}) RETURN t.normalizedDistance", Double.class), 0.001);
        assertEquals(Double.valueOf(0.00), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.b\"}) RETURN t.normalizedDistance", Double.class), 0.001);
        assertEquals(Double.valueOf(0.25), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.c\"}) RETURN t.normalizedDistance", Double.class), 0.001);
        assertEquals(Double.valueOf(1.00), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.d\"}) RETURN t.normalizedDistance", Double.class), 0.001);
        assertEquals(Double.valueOf(1.00), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.e\"}) RETURN t.normalizedDistance", Double.class), 0.001);
        store.commitTransaction();
    }

}

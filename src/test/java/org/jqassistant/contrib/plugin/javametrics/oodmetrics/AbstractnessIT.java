package org.jqassistant.contrib.plugin.javametrics.oodmetrics;

import static org.junit.Assert.assertEquals;

import com.buschmais.jqassistant.core.report.api.model.Result;
import com.buschmais.jqassistant.core.rule.api.model.Concept;
import com.buschmais.jqassistant.core.rule.api.model.Severity;
import org.jqassistant.contrib.plugin.javametrics.AbstractMetricsIT;
import org.junit.Test;

public class AbstractnessIT extends AbstractMetricsIT {

    @Test
    public void whenApplyNumberClasses_expectValue() throws Exception {
        // arrange
        scanClassPathDirectory(getClassesDirectory(AbstractnessIT.class));

        // act
        Result<Concept> result = applyConcept("ood-metrics:NumberClasses");

        // assert
        assertEquals(Result.Status.SUCCESS, result.getStatus());
        assertEquals(Severity.MINOR, result.getSeverity());

        store.beginTransaction();
        assertEquals(Long.valueOf(1), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.a\"}) RETURN t.nc", Long.class));
        assertEquals(Long.valueOf(2), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.b\"}) RETURN t.nc", Long.class));
        assertEquals(Long.valueOf(2), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.c\"}) RETURN t.nc", Long.class));
        assertEquals(Long.valueOf(2), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.d\"}) RETURN t.nc", Long.class));
        assertEquals(Long.valueOf(2), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.e\"}) RETURN t.nc", Long.class));
        store.commitTransaction();
    }

    @Test
    public void whenApplyNumberAbstract_expectValue() throws Exception {
        // arrange
        scanClassPathDirectory(getClassesDirectory(AbstractnessIT.class));

        // act
        Result<Concept> result = applyConcept("ood-metrics:NumberAbstracts");

        // assert
        assertEquals(Result.Status.SUCCESS, result.getStatus());
        assertEquals(Severity.MINOR, result.getSeverity());

        store.beginTransaction();
        assertEquals(Long.valueOf(1), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.a\"}) RETURN t.na", Long.class));
        assertEquals(Long.valueOf(1), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.b\"}) RETURN t.na", Long.class));
        assertEquals(Long.valueOf(0), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.c\"}) RETURN t.na", Long.class));
        assertEquals(Long.valueOf(2), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.d\"}) RETURN t.na", Long.class));
        assertEquals(Long.valueOf(0), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.e\"}) RETURN t.na", Long.class));
        store.commitTransaction();
    }

    @Test
    public void whenApplyAbstractness_expectValue() throws Exception {
        // arrange
        scanClassPathDirectory(getClassesDirectory(AbstractnessIT.class));

        // act
        Result<Concept> result = applyConcept("ood-metrics:Abstractness");

        // assert
        assertEquals(Result.Status.SUCCESS, result.getStatus());
        assertEquals(Severity.MINOR, result.getSeverity());

        store.beginTransaction();
        assertEquals(Double.valueOf(1.0), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.a\"}) RETURN t.abstractness", Double.class), 0.001);
        assertEquals(Double.valueOf(0.5), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.b\"}) RETURN t.abstractness", Double.class), 0.001);
        assertEquals(Double.valueOf(0.0), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.c\"}) RETURN t.abstractness", Double.class), 0.001);
        assertEquals(Double.valueOf(1.0), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.d\"}) RETURN t.abstractness", Double.class), 0.001);
        assertEquals(Double.valueOf(0.0), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.e\"}) RETURN t.abstractness", Double.class), 0.001);
        store.commitTransaction();
    }

}

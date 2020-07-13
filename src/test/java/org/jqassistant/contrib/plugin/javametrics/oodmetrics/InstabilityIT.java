package org.jqassistant.contrib.plugin.javametrics.oodmetrics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.buschmais.jqassistant.core.report.api.model.Result;
import com.buschmais.jqassistant.core.rule.api.model.Concept;
import com.buschmais.jqassistant.core.rule.api.model.Severity;
import org.jqassistant.contrib.plugin.javametrics.AbstractMetricsIT;
import org.junit.jupiter.api.Test;

public class InstabilityIT extends AbstractMetricsIT {

    @Test
    public void whenApplyAfferentCouplings_expectValue() throws Exception {
        // arrange
        scanClassPathDirectory(getClassesDirectory(InstabilityIT.class));

        // act
        Result<Concept> result = applyConcept("ood-metrics:AfferentCouplings");

        // assert
        assertEquals(Result.Status.SUCCESS, result.getStatus());
        assertEquals(Severity.MINOR, result.getSeverity());

        store.beginTransaction();
        assertEquals(Long.valueOf(3), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.a\"}) RETURN t.ca", Long.class));
        assertEquals(Long.valueOf(3), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.b\"}) RETURN t.ca", Long.class));
        assertEquals(Long.valueOf(1), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.c\"}) RETURN t.ca", Long.class));
        assertEquals(Long.valueOf(0), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.d\"}) RETURN t.ca", Long.class));
        assertEquals(Long.valueOf(2), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.e\"}) RETURN t.ca", Long.class));
        store.commitTransaction();
    }

    @Test
    public void whenApplyEfferentCouplings_expectValue() throws Exception {
        // arrange
        scanClassPathDirectory(getClassesDirectory(InstabilityIT.class));

        // act
        Result<Concept> result = applyConcept("ood-metrics:EfferentCouplings");

        // assert
        assertEquals(Result.Status.SUCCESS, result.getStatus());
        assertEquals(Severity.MINOR, result.getSeverity());

        store.beginTransaction();
        assertEquals(Long.valueOf(1), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.a\"}) RETURN t.ce", Long.class));
        assertEquals(Long.valueOf(3), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.b\"}) RETURN t.ce", Long.class));
        assertEquals(Long.valueOf(3), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.c\"}) RETURN t.ce", Long.class));
        assertEquals(Long.valueOf(2), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.d\"}) RETURN t.ce", Long.class));
        assertEquals(Long.valueOf(0), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.e\"}) RETURN t.ce", Long.class));
        store.commitTransaction();
    }

    @Test
    public void whenApplyInstablity_expectValue() throws Exception {
        // arrange
        scanClassPathDirectory(getClassesDirectory(InstabilityIT.class));

        // act
        Result<Concept> result = applyConcept("ood-metrics:Instability");

        // assert
        assertEquals(Result.Status.SUCCESS, result.getStatus());
        assertEquals(Severity.MINOR, result.getSeverity());

        store.beginTransaction();
        assertEquals(Double.valueOf(0.25), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.a\"}) RETURN t.instability", Double.class), 0.001);
        assertEquals(Double.valueOf(0.50), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.b\"}) RETURN t.instability", Double.class), 0.001);
        assertEquals(Double.valueOf(0.75), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.c\"}) RETURN t.instability", Double.class), 0.001);
        assertEquals(Double.valueOf(1.00), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.d\"}) RETURN t.instability", Double.class), 0.001);
        assertEquals(Double.valueOf(0.00), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.oodmetrics.e\"}) RETURN t.instability", Double.class), 0.001);
        store.commitTransaction();
    }

}

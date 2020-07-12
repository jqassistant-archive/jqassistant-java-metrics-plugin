package org.jqassistant.contrib.plugin.javametrics.visibilitymetrics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.buschmais.jqassistant.core.report.api.model.Result;
import com.buschmais.jqassistant.core.rule.api.model.Concept;
import com.buschmais.jqassistant.core.rule.api.model.Severity;
import org.jqassistant.contrib.plugin.javametrics.AbstractMetricsIT;
import org.junit.jupiter.api.Test;

public class RelativeVisibilityIT extends AbstractMetricsIT {

    @Test
    public void whenApplyNumberClasses_expectValue() throws Exception {
        // arrange
        scanClassPathDirectory(getClassesDirectory(RelativeVisibilityIT.class));

        // act
        Result<Concept> result = applyConcept("visibility-metrics:RelativeVisibility");

        // assert
        assertEquals(Result.Status.SUCCESS, result.getStatus());
        assertEquals(Severity.MINOR, result.getSeverity());

        store.beginTransaction();
        assertEquals(Double.valueOf(0.5), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.visibilitymetrics.a\"}) RETURN t.relativeVisibility", Double.class));
        assertEquals(Double.valueOf(1.0), retrieveSingleValue("MATCH (t:Package {fqn:\"org.jqassistant.contrib.plugin.javametrics.visibilitymetrics.b\"}) RETURN t.relativeVisibility", Double.class));
        store.commitTransaction();
    }

}

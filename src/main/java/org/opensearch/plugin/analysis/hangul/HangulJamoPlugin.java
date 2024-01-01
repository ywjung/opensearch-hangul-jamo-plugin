package org.opensearch.plugin.analysis.hangul;

import org.opensearch.index.analysis.HangulChosungFilterFactory;
import org.opensearch.index.analysis.HangulJamoTokenFilterFactory;
import org.opensearch.index.analysis.TokenFilterFactory;
import org.opensearch.indices.analysis.AnalysisModule;
import org.opensearch.plugins.AnalysisPlugin;
import org.opensearch.plugins.Plugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by changoh on 17. 3. 20.
 */
public class HangulJamoPlugin extends Plugin implements AnalysisPlugin {

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<TokenFilterFactory>> getTokenFilters() {
        Map<String, AnalysisModule.AnalysisProvider<TokenFilterFactory>> extra = new HashMap<>();
        extra.put("hangul_jamo", HangulJamoTokenFilterFactory::new);
        extra.put("hangul_chosung", HangulChosungFilterFactory::new);
        return extra;
    }
}

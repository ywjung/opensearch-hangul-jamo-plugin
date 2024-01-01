package org.opensearch.index.analysis;

import org.apache.lucene.analysis.TokenStream;
import org.opensearch.common.settings.Settings;
import org.opensearch.env.Environment;
import org.opensearch.index.IndexSettings;
import org.opensearch.index.analysis.parser.HangulJamoParserType;

/**
 * Created by changoh on 17. 3. 22.
 */
public class HangulChosungFilterFactory extends AbstractTokenFilterFactory {
    private HangulJamoParserType parserType;

    public HangulChosungFilterFactory(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        super(indexSettings, name, settings);
        this.parserType = HangulJamoParserType.getParserTypeByString(HangulJamoParserType.CHOSUNG.name());
    }

    public TokenStream create(TokenStream tokenStream) {
        return new HangulJamoTokenFilter(tokenStream, parserType);
    }
}

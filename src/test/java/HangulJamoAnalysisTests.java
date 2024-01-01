import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.opensearch.common.settings.Settings;
import org.opensearch.core.index.Index;
import org.opensearch.index.analysis.HangulChosungFilterFactory;
import org.opensearch.index.analysis.HangulJamoTokenFilterFactory;
import org.opensearch.index.analysis.TokenFilterFactory;
import org.opensearch.plugin.analysis.hangul.HangulJamoPlugin;
import org.opensearch.test.OpenSearchTestCase;

import java.io.IOException;
import java.io.StringReader;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by changoh on 17. 3. 21.
 */
public class HangulJamoAnalysisTests extends OpenSearchTestCase {

    public void testHangulJamoTokenFilter() throws IOException {
        Settings settings = Settings.builder().build();
        TestAnalysis analysis = createTestAnalysis(new Index("test", "_na"), settings, new HangulJamoPlugin());

        TokenFilterFactory tokenFilter = analysis.tokenFilter.get("hangul_jamo");
        assertTrue(tokenFilter instanceof HangulJamoTokenFilterFactory);

        String source = "사랑하는 사람들.,!";
        String[] expected = new String[]{"ㅅㅏㄹㅏㅇㅎㅏㄴㅡㄴ", "ㅅㅏㄹㅏㅁㄷㅡㄹ"};
        Tokenizer tokenizer = new StandardTokenizer();
        tokenizer.setReader(new StringReader(source));
        assertSimpleTSOutput(tokenFilter.create(tokenizer), expected);
    }

    public void testHangulChosungTokenFilter() throws IOException {
        Settings settings = Settings.builder().build();
        TestAnalysis analysis = createTestAnalysis(new Index("test", "_na"), settings, new HangulJamoPlugin());

        TokenFilterFactory tokenFilter = analysis.tokenFilter.get("hangul_chosung");
        assertTrue(tokenFilter instanceof HangulChosungFilterFactory);
        String source = "사랑하는 사람들.,!";
        String[] expected = new String[]{"ㅅㄹㅎㄴ", "ㅅㄹㄷ"};
        Tokenizer tokenizer = new StandardTokenizer();
        tokenizer.setReader(new StringReader(source));
        assertSimpleTSOutput(tokenFilter.create(tokenizer), expected);
    }

    public static void assertSimpleTSOutput(TokenStream stream,
                                            String[] expected) throws IOException {
        stream.reset();
        CharTermAttribute termAttr = stream.getAttribute(CharTermAttribute.class);
        assertThat(termAttr, notNullValue());
        int i = 0;
        while (stream.incrementToken()) {
            assertThat(expected.length, greaterThan(i));
            assertEquals( "expected different term at index " + i, expected[i], termAttr.toString());
            i++;
        }
        assertThat("not all tokens produced", i, equalTo(expected.length));
    }

}

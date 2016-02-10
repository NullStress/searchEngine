import com.lund.IndexSearcher;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Kristian
 * Date: 10.02.2016
 * Time: 18:40
 */
public class SearchMatcherTest {

  @Test
  public void computeSearchMatchCount() {
    Map<String,Map<String, Integer>> indexMap = new HashMap<>();
    Map<String, Integer> aMap = new HashMap<>();
    aMap.put("doc1", 2);
    aMap.put("doc3", 1);
    aMap.put("doc4", 1);

    Map<String, Integer> treeMap = new HashMap<>();
    treeMap.put("doc2", 2);
    treeMap.put("doc4", 1);
    indexMap.put("tree", treeMap);
    indexMap.put("a", aMap);
    Map<String, Integer> results = IndexSearcher.computeSearchMatchCount(new String[]{"a", "tree"}, indexMap);

    assertTrue(results.get("doc1") == 1);
    assertTrue(results.get("doc2") == 1);
    assertTrue(results.get("doc3") == 1);
    assertTrue(results.get("doc4") == 2);
  }
}

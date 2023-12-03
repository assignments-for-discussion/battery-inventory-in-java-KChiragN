package bunchbysoh;

public class Main {
  static class CountsBySoH {
    public int healthy = 0;
    public int exchange = 0;
    public int failed = 0;
  };

  static CountsBySoH countBatteriesByHealth(int[] presentCapacities) {
    CountsBySoH counts = new CountsBySoH();
     for (int capacity : presentCapacities) {
      double soh = (double) capacity / 120.0 * 100.0;
      if (soh > 80)
      {
        counts.healthy++;
      } 
      else if (soh >= 62)
      {
        counts.exchange++;
      }
      else 
      {
        counts.failed++;
      }
    }

    return counts;
  }

  static void testBucketingByHealth() {
    System.out.println("Counting batteries by SoH...\n");
    int[] presentCapacities = {113, 116, 80, 95, 92, 70};
    CountsBySoH counts = countBatteriesByHealth(presentCapacities);
    assert(counts.healthy == 2);
    assert(counts.exchange == 3);
    assert(counts.failed == 1);
    int[] zeroSoHCapacities = {0, 0, 0}; //when batteries have 0% SoH
    CountsBySoH zeroSoHCounts = countBatteriesByHealth(zeroSoHCapacities);
    assert(zeroSoHCounts.healthy == 0);
    assert(zeroSoHCounts.exchange == 0);
    assert(zeroSoHCounts.failed == 3);
    int[] edgeCaseCapacities = {96, 96, 96, 96}; // 80% of 120Ah capacity
    CountsBySoH edgeCaseCounts = countBatteriesByHealth(edgeCaseCapacities);
    assert(edgeCaseCounts.healthy == 3);
    assert(edgeCaseCounts.exchange == 0);
    assert(edgeCaseCounts.failed == 0);
    int[] edgeCaseCapacities = {74, 74, 74}; // 62% of 120Ah capacity
    CountsBySoH edgeCaseCounts = countBatteriesByHealth(edgeCaseCapacities);
    assert(edgeCaseCounts.healthy == 0);
    assert(edgeCaseCounts.exchange == 3);
    assert(edgeCaseCounts.failed == 0);
    int[] emptyArray = {}; //when input array is empty
    CountsBySoH emptyCounts = countBatteriesByHealth(emptyArray);
    assert(emptyCounts.healthy == 0);
    assert(emptyCounts.exchange == 0);
    assert(emptyCounts.failed == 0);
    assert(presentCapacities.length == counts.healthy + counts.exchange + counts.failed);//Total count of batteries is equal to no of batteries in the array
    System.out.println("Done counting :)\n");
  }

  public static void main(String[] args) {
    testBucketingByHealth();
  }
}

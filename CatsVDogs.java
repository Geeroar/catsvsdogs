import java.util.*;

public class CatsVDogs {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();

        while (testCases > 0) {
            int cats = sc.nextInt();
            int dogs = sc.nextInt();
            int viewers = sc.nextInt();

            List<Bucket> buckets = new ArrayList<>();
            buckets.add(new Bucket());
            while (viewers > 0) {
                String[] vote = new String[2];
                vote[0] = sc.next();
                vote[1] = sc.next();
                boolean added = false;
                for (Bucket bucket : buckets) {
                    if (bucket.isCompatible(vote)) {
                        bucket.addVote(vote);
                        added = true;
                    }
                }
                if (!added) {
                    Bucket newBucket = new Bucket();
                    newBucket.addVote(vote);
                    for (Bucket bucket : buckets) {
                        newBucket.fillCompatibleFromOtherBucket(bucket);
                    }
                    buckets.add(newBucket);
                }
                viewers--;
            }
            int result = buckets.stream()
                    .mapToInt(b -> b.count)
                    .max()
                    .getAsInt();
            System.out.println("" + result);
            testCases--;
        }
    }

    static class Bucket {
        Set<String> keepers;
        Set<String> dumpers;
        Set<String[]> votes;
        int count;

        Bucket() {
            keepers = new HashSet<>();
            dumpers = new HashSet<>();
            votes = new HashSet<>();
            count = 0;
        }

        boolean isCompatible(String[] vote) {
            return !dumpers.contains(vote[0]) && !keepers.contains(vote[1]);
        }

        void fillCompatibleFromOtherBucket(Bucket bucket) {
            for (String[] vote : bucket.votes) {
                if (isCompatible(vote)) {
                    addVote(vote);
                }
            }
        }

        void addVote(String[] vote) {
            keepers.add(vote[0]);
            dumpers.add(vote[1]);
            votes.add(vote);
            count++;
        }
    }

}

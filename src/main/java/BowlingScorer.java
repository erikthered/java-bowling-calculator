public class BowlingScorer {
  public static void main(String... args) {
    if (args.length != 1) throw new RuntimeException("Wrong number of args");

    String[] frames = args[0].toLowerCase().split("-");

    if (frames.length != 10) throw new RuntimeException("Bad score string");

    int ttlScore = 0;
    for (int i=0; i<frames.length; i++) {
      ttlScore += getScoreForFrame(frames, i);
      System.out.println(String.format("Scoring frame %s: %s", i+1, ttlScore));
    }

    System.out.println(String.format("Final score: %s", ttlScore));
  }

  public static int getScoreForFrame(String[] frames, int num) {
    if (num == 9) { // I'm too lazy to fight with a one-based index
      if (frames[num].length() == 3) {
        if (frames[num].equals("xxx")) {
          return 30;
        } else if (frames[num].startsWith("xx")) {
          return 20 + Character.getNumericValue(frames[num].charAt(2));
        } else if (frames[num].startsWith("x")) {
          if (frames[num].endsWith("/")) {
            return 20;
          } else {
            return 10 + Character.getNumericValue(frames[num].charAt(1)) + Character
                .getNumericValue(frames[num].charAt(2));
          }
        } else if (frames[num].charAt(1) == '/') {
          if (frames[num].charAt(2) == 'x') {
            return 20;
          } else {
            return 10 + Character.getNumericValue(frames[num].charAt(2));
          }
        }
      } else {
        return (Character.getNumericValue(frames[num].charAt(0)) + Character
            .getNumericValue(frames[num].charAt(1)));
      }
    } else if (frames[num].startsWith("x")) {
      int score = 10;
      if (frames[num + 1].startsWith("x")) {
        if(num == 8){
         if (frames[num + 1].charAt(1) == 'x'){
           score += 20;
         }else if(frames[num+1].charAt(1) == '/'){
           score += 10;
         }else{
           score += (10+Character.getNumericValue(frames[num+1].charAt(1)));
         }
        } else if (frames[num + 2].startsWith("x")) {
          score += 20;
        } else {
          score += (10 + Character.getNumericValue(frames[num + 2].charAt(0)));
        }
      } else if (frames[num + 1].endsWith("/")) {
        score += 10;
      } else {
        score += (Character.getNumericValue(frames[num + 1].charAt(0)) + Character
            .getNumericValue(frames[num + 1].charAt(1)));
      }
      return score;
    } else if (frames[num].endsWith("/")) {
      char mark = frames[num + 1].charAt(0);
      if (mark == 'x') {
        return 20;
      } else {
        return 10 + Character.getNumericValue(mark);
      }
    } else {
      return Integer.parseInt(frames[num].substring(0, 1)) + Integer.parseInt(frames[num].substring(1, 2));
    }
    return 0;
  }

}

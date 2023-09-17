package poker.holdEm.texas;

public class TableResults {
    public static void determineWinCombs(boolean[] winners, Combination[] combs) {
        int compar;

        for (int i = 0; i < winners.length; i++)
            winners[i] = false;
        for (int i = 0; i < combs.length; i++) {
            if (combs[i] != null) {
                compar = 1;
                for (int j = 0; j < i; j++) {
                    if (combs[j] != null) {
                        if (Comparison(combs[i], combs[j]) == -1) {
                            compar = -1;
                            break;
                        } else if (Comparison(combs[i], combs[j]) == 0)
                            compar = 0;
                    }
                }
                if (compar == 0)
                    winners[i] = true;
                else if (compar == 1) {
                    winners[i] = true;
                    for (int j = 0; j < i; j++)
                        winners[j] = false;
                }
            }
        }
    }

    public static int Comparison(Combination comb1, Combination comb2) {
        int result = -1;

        if (comb1.getPriority() > comb2.getPriority())
            result = 1;
        else if (comb1.getPriority() == comb2.getPriority()) {
            switch (comb1.getPriority()) {
                case 0:
                    result = bestOfHighestCard(comb1, comb2);
                    break;
                case 1:
                    result = bestOfPair(comb1, comb2);
                    break;
                case 2:
                    result = bestOfTwoPairs(comb1, comb2);
                    break;
                case 3:
                    result = bestOfThree(comb1, comb2);
                    break;
                case 4:
                    result = bestOfStraight(comb1, comb2);
                    break;
                case 5:
                    result = bestOfFlush(comb1, comb2);
                    break;
                case 6:
                    result = bestOfFullHouse(comb1, comb2);
                    break;
                case 7:
                    result = bestOfFour(comb1, comb2);
                    break;
                case 8:
                    result = bestOfStraightFlush(comb1, comb2);
                    break;
            }
        }
        return result;
    }

    public static int bestOfHighestCard(Combination comb1, Combination comb2) {
        int result = 0;
        int rank1;
        int rank2;

        for (int i = 4; i >= 0; i--) {
            rank1 = comb1.getCard(i).getDeckNumber() / 4;
            rank2 = comb2.getCard(i).getDeckNumber() / 4;
            if (rank1 > rank2) {
                result = 1;
                break;
            } else if (rank1 < rank2) {
                result = -1;
                break;
            }
        }
        return result;
    }

    public static int bestOfPair(Combination comb1, Combination comb2) {
        int result = 0;
        int h, rank;
        int rankP1 = 0;
        int rankP2 = 0;                        // ranks of pair
        int[] rankH1 = new int[3];
        int[] rankH2 = new int[3];            // ranks of highest cards

        h = 0;
        for (int i = 0; i < 5; i++) {
            rank = comb1.getCard(i).getDeckNumber() / 4;
            if (i < 4) {
                if (rank == comb1.getCard(i + 1).getDeckNumber() / 4) {
                    rankP1 = rank;
                    i++;
                } else
                    rankH1[h++] = rank;
            } else
                rankH1[h] = rank;
        }
        h = 0;
        for (int i = 0; i < 5; i++) {
            rank = comb2.getCard(i).getDeckNumber() / 4;
            if (i < 4) {
                if (rank == comb2.getCard(i + 1).getDeckNumber() / 4) {
                    rankP2 = rank;
                    i++;
                } else
                    rankH2[h++] = rank;
            } else
                rankH2[h] = rank;
        }
        if (rankP1 > rankP2)
            result = 1;
        else if (rankP1 < rankP2)
            result = -1;
        else {
            if (rankH1[2] > rankH2[2])
                result = 1;
            else if (rankH1[2] < rankH2[2])
                result = -1;
            else {
                if (rankH1[1] > rankH2[1])
                    result = 1;
                else if (rankH1[1] < rankH2[1])
                    result = -1;
                else {
                    if (rankH1[0] > rankH2[0])
                        result = 1;
                    else if (rankH1[0] < rankH2[0])
                        result = -1;
                }
            }
        }
        return result;
    }

    public static int bestOfTwoPairs(Combination comb1, Combination comb2) {
        int result = 0;
        int p, rank;
        int[] rankP1 = new int[2];
        int[] rankP2 = new int[2];            // ranks of pairs
        int rankH1 = 0;
        int rankH2 = 0;                        // ranks of highest card

        p = 0;
        for (int i = 0; i < 5; i++) {
            rank = comb1.getCard(i).getDeckNumber() / 4;
            if (i < 4) {
                if (rank == comb1.getCard(i + 1).getDeckNumber() / 4) {
                    rankP1[p++] = rank;
                    i++;
                } else
                    rankH1 = rank;
            } else
                rankH1 = rank;
        }
        p = 0;
        for (int i = 0; i < 5; i++) {
            rank = comb2.getCard(i).getDeckNumber() / 4;
            if (i < 4) {
                if (rank == comb2.getCard(i + 1).getDeckNumber() / 4) {
                    rankP2[p++] = rank;
                    i++;
                } else
                    rankH2 = rank;
            } else
                rankH2 = rank;
        }
        if (rankP1[1] > rankP2[1])
            result = 1;
        else if (rankP1[1] < rankP2[1])
            result = -1;
        else {
            if (rankP1[0] > rankP2[0])
                result = 1;
            else if (rankP1[0] < rankP2[0])
                result = -1;
            else {
                if (rankH1 > rankH2)
                    result = 1;
                else if (rankH1 < rankH2)
                    result = -1;
            }
        }
        return result;
    }

    public static int bestOfThree(Combination comb1, Combination comb2) {
        int result = 0;
        int h, rank;
        int rankT1 = 0;
        int rankT2 = 0;                        // ranks of three
        int[] rankH1 = new int[2];
        int[] rankH2 = new int[2];            // ranks of highest cards

        h = 0;
        for (int i = 0; i < 5; i++) {
            rank = comb1.getCard(i).getDeckNumber() / 4;
            if (i < 3) {
                if (rank == comb1.getCard(i + 1).getDeckNumber() / 4) {
                    rankT1 = rank;
                    i += 2;
                } else
                    rankH1[h++] = rank;
            } else
                rankH1[h++] = rank;
        }
        h = 0;
        for (int i = 0; i < 5; i++) {
            rank = comb2.getCard(i).getDeckNumber() / 4;
            if (i < 3) {
                if (rank == comb2.getCard(i + 1).getDeckNumber() / 4) {
                    rankT2 = rank;
                    i += 2;
                } else
                    rankH2[h++] = rank;
            } else
                rankH2[h++] = rank;
        }
        if (rankT1 > rankT2)
            result = 1;
        else if (rankT1 < rankT2)
            result = -1;
        else {
            if (rankH1[1] > rankH2[1])
                result = 1;
            else if (rankH1[1] < rankH2[1])
                result = -1;
            else {
                if (rankH1[0] > rankH2[0])
                    result = 1;
                else if (rankH1[0] < rankH2[0])
                    result = -1;
            }
        }
        return result;
    }

    public static int bestOfStraight(Combination comb1, Combination comb2) {
        int result = 0;
        int rank1;
        int rank2;

        if (comb1.getCard(0).getDeckNumber() / 4 == 0 &&
                comb1.getCard(4).getDeckNumber() / 4 == 12)
            rank1 = -1;
        else
            rank1 = comb1.getCard(0).getDeckNumber() / 4;
        if (comb2.getCard(0).getDeckNumber() / 4 == 0 &&
                comb2.getCard(4).getDeckNumber() / 4 == 12)
            rank2 = -1;
        else
            rank2 = comb2.getCard(0).getDeckNumber() / 4;
        if (rank1 > rank2)
            result = 1;
        else if (rank1 < rank2)
            result = -1;
        return result;
    }

    public static int bestOfFlush(Combination comb1, Combination comb2) {
        int result = 0;
        int[] rank1 = new int[5];
        int[] rank2 = new int[5];

        for (int i = 0; i < 5; i++) {
            rank1[i] = comb1.getCard(i).getDeckNumber() / 4;
            rank2[i] = comb2.getCard(i).getDeckNumber() / 4;
        }
        if (rank1[4] > rank2[4])
            result = 1;
        else if (rank1[4] < rank2[4])
            result = -1;
        else {
            if (rank1[3] > rank2[3])
                result = 1;
            else if (rank1[3] < rank2[3])
                result = -1;
            else {
                if (rank1[2] > rank2[2])
                    result = 1;
                else if (rank1[2] < rank2[2])
                    result = -1;
                else {
                    if (rank1[1] > rank2[1])
                        result = 1;
                    else if (rank1[1] < rank2[1])
                        result = -1;
                    else {
                        if (rank1[0] > rank2[0])
                            result = 1;
                        else if (rank1[0] < rank2[0])
                            result = -1;
                    }
                }
            }
        }
        return result;
    }

    public static int bestOfFullHouse(Combination comb1, Combination comb2) {
        int result = 0;
        int rankP1;
        int rankP2;                    // ranks of pair
        int rankT1;
        int rankT2;                    // ranks of three

        if (comb1.getCard(0).getDeckNumber() / 4 == comb1.getCard(2).getDeckNumber() / 4) {
            rankT1 = comb1.getCard(0).getDeckNumber() / 4;
            rankP1 = comb1.getCard(3).getDeckNumber() / 4;
        } else {
            rankP1 = comb1.getCard(0).getDeckNumber() / 4;
            rankT1 = comb1.getCard(2).getDeckNumber() / 4;
        }
        if (comb2.getCard(0).getDeckNumber() / 4 == comb2.getCard(2).getDeckNumber() / 4) {
            rankT2 = comb2.getCard(0).getDeckNumber() / 4;
            rankP2 = comb2.getCard(3).getDeckNumber() / 4;
        } else {
            rankP2 = comb2.getCard(0).getDeckNumber() / 4;
            rankT2 = comb2.getCard(2).getDeckNumber() / 4;
        }
        if (rankT1 > rankT2)
            result = 1;
        else if (rankT1 < rankT2)
            result = -1;
        else {
            if (rankP1 > rankP2)
                result = 1;
            else if (rankP1 < rankP2)
                result = -1;
        }
        return result;
    }

    public static int bestOfFour(Combination comb1, Combination comb2) {
        int result = 0;
        int rankF1;
        int rankF2;                        // ranks of four
        int rankH1;
        int rankH2;                        // ranks of highest cards

        if (comb1.getCard(0).getDeckNumber() / 4 == comb1.getCard(3).getDeckNumber() / 4) {
            rankF1 = comb1.getCard(0).getDeckNumber() / 4;
            rankH1 = comb1.getCard(4).getDeckNumber() / 4;
        } else {
            rankH1 = comb1.getCard(0).getDeckNumber() / 4;
            rankF1 = comb1.getCard(1).getDeckNumber() / 4;
        }
        if (comb2.getCard(0).getDeckNumber() / 4 == comb2.getCard(3).getDeckNumber() / 4) {
            rankF2 = comb2.getCard(0).getDeckNumber() / 4;
            rankH2 = comb2.getCard(4).getDeckNumber() / 4;
        } else {
            rankH2 = comb2.getCard(0).getDeckNumber() / 4;
            rankF2 = comb2.getCard(1).getDeckNumber() / 4;
        }
        if (rankF1 > rankF2)
            result = 1;
        else if (rankF1 < rankF2)
            result = -1;
        else {
            if (rankH1 > rankH2)
                result = 1;
            else if (rankH1 < rankH2)
                result = -1;
        }
        return result;
    }

    public static int bestOfStraightFlush(Combination comb1, Combination comb2) {
        int result = 0;
        int rank1;
        int rank2;

        if (comb1.getCard(0).getDeckNumber() / 4 == 0 && comb1.getCard(4).getDeckNumber() / 4 == 12)
            rank1 = -1;
        else
            rank1 = comb1.getCard(0).getDeckNumber() / 4;
        if (comb2.getCard(0).getDeckNumber() / 4 == 0 && comb2.getCard(4).getDeckNumber() / 4 == 12)
            rank2 = -1;
        else
            rank2 = comb2.getCard(0).getDeckNumber() / 4;
        if (rank1 > rank2)
            result = 1;
        else if (rank1 < rank2)
            result = -1;
        return result;
    }
}

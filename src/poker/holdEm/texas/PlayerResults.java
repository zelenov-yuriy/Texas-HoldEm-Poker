package poker.holdEm.texas;

public interface PlayerResults {
    default Combination determineBest(Combination[] variants) {
        Combination best;

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < (20 - i); j++) {
                if (variants[j].getPriority() < variants[j + 1].getPriority()) {
                    Combination temp = variants[j];
                    variants[j] = variants[j + 1];
                    variants[j + 1] = temp;
                }
            }
        }
        if (variants[0].getPriority() > variants[1].getPriority())
            best = variants[0];
        else
            best = bestOf(variants);
        return best;
    }

    default Combination bestOf(Combination[] variants) {
        Combination best = variants[0];
        int priority = variants[0].getPriority();

        switch (priority) {
            case 0 -> best = bestOfHighestCard(variants);
            case 1 -> best = bestOfPair(variants);
            case 2 -> best = bestOfTwoPairs(variants);
            case 3 -> best = bestOfThree(variants);
            case 4 -> best = bestOfStraight(variants);
            case 5 -> best = bestOfFlush(variants);
            case 6 -> best = bestOfFullHouse(variants);
            case 7 -> best = bestOfFour(variants);
            case 8 -> best = bestOfStraightFlush(variants);
        }
        return best;
    }

    default Combination bestOfHighestCard(Combination[] variants) {
        Combination best = variants[0];
        int rank1;
        int rank2;

        for (int i = 1; i < 21; i++) {
            rank1 = best.getCard(4).getDeckNumber() / 4;
            rank2 = variants[i].getCard(4).getDeckNumber() / 4;
            if (rank2 > rank1)
                best = variants[i];
            else if (rank2 == rank1) {
                rank1 = best.getCard(3).getDeckNumber() / 4;
                rank2 = variants[i].getCard(3).getDeckNumber() / 4;
                if (rank2 > rank1)
                    best = variants[i];
                else if (rank2 == rank1) {
                    rank1 = best.getCard(2).getDeckNumber() / 4;
                    rank2 = variants[i].getCard(2).getDeckNumber() / 4;
                    if (rank2 > rank1)
                        best = variants[i];
                    else if (rank2 == rank1) {
                        rank1 = best.getCard(1).getDeckNumber() / 4;
                        rank2 = variants[i].getCard(1).getDeckNumber() / 4;
                        if (rank2 > rank1)
                            best = variants[i];
                        else if (rank2 == rank1) {
                            rank1 = best.getCard(0).getDeckNumber() / 4;
                            rank2 = variants[i].getCard(0).getDeckNumber() / 4;
                            if (rank2 > rank1)
                                best = variants[i];
                        }
                    }
                }
            }
        }
        return best;
    }

    default Combination bestOfPair(Combination[] variants) {
        int i = 0;
        Combination best = variants[i++];
        int h, rank;
        int rankP1 = 0;
        int rankP2 = 0;                        // ranks of pair
        int[] rankH1 = new int[3];
        int[] rankH2 = new int[3];            // ranks of highest cards

        while (variants[i].getPriority() == 1) {
            h = 0;
            for (int j = 0; j < 5; j++) {
                rank = best.getCard(j).getDeckNumber() / 4;
                if (j < 4) {
                    if (best.getCard(j + 1).getDeckNumber() / 4 == rank) {
                        rankP1 = rank;
                        j++;
                    } else
                        rankH1[h++] = rank;
                } else
                    rankH1[h] = rank;
            }
            h = 0;
            for (int j = 0; j < 5; j++) {
                rank = variants[i].getCard(j).getDeckNumber() / 4;
                if (j < 4) {
                    if (variants[i].getCard(j + 1).getDeckNumber() / 4 == rank) {
                        rankP2 = rank;
                        j++;
                    } else
                        rankH2[h++] = rank;
                } else
                    rankH2[h] = rank;
            }
            if (rankP2 > rankP1)
                best = variants[i];
            else if (rankP2 == rankP1) {
                if (rankH2[2] > rankH1[2])
                    best = variants[i];
                else if (rankH2[2] == rankH1[2]) {
                    if (rankH2[1] > rankH1[1])
                        best = variants[i];
                    else if (rankH1[1] == rankH1[1]) {
                        if (rankH2[0] > rankH1[0])
                            best = variants[i];
                    }
                }
            }
            i++;
        }
        return best;
    }

    default Combination bestOfTwoPairs(Combination[] variants) {
        int i = 0;
        Combination best = variants[i++];
        int p, rank;
        int[] rankP1 = new int[2];
        int[] rankP2 = new int[2];            // ranks of pairs
        int rankH1 = 0;
        int rankH2 = 0;                        // ranks of highest card

        while (variants[i].getPriority() == 2) {
            p = 0;
            for (int j = 0; j < 5; j++) {
                rank = best.getCard(j).getDeckNumber() / 4;
                if (j < 4) {
                    if (best.getCard(j + 1).getDeckNumber() / 4 == rank) {
                        rankP1[p++] = rank;
                        j++;
                    } else
                        rankH1 = rank;
                } else
                    rankH1 = rank;
            }
            p = 0;
            for (int j = 0; j < 5; j++) {
                rank = variants[i].getCard(j).getDeckNumber() / 4;
                if (j < 4) {
                    if (variants[i].getCard(j + 1).getDeckNumber() / 4 == rank) {
                        rankP2[p++] = rank;
                        j++;
                    } else
                        rankH2 = rank;
                } else
                    rankH2 = rank;
            }
            if (rankP2[1] > rankP1[1])
                best = variants[i];
            else if (rankP2[1] == rankP1[1]) {
                if (rankP2[0] > rankP1[0])
                    best = variants[i];
                else if (rankP2[0] == rankP1[0]) {
                    if (rankH2 > rankH1)
                        best = variants[i];
                }
            }
            i++;
        }
        return best;
    }

    default Combination bestOfThree(Combination[] variants) {
        int i = 0;
        Combination best = variants[i++];
        int h, rank;
        int rankT1 = 0;
        int rankT2 = 0;                        // ranks of three
        int[] rankH1 = new int[2];
        int[] rankH2 = new int[2];            // ranks of highest cards

        while (variants[i].getPriority() == 3) {
            h = 0;
            for (int j = 0; j < 5; j++) {
                rank = best.getCard(j).getDeckNumber() / 4;
                if (j < 3) {
                    if (best.getCard(j + 1).getDeckNumber() / 4 == rank) {
                        rankT1 = rank;
                        j += 2;
                    } else
                        rankH1[h++] = rank;
                } else
                    rankH1[h++] = rank;
            }
            h = 0;
            for (int j = 0; j < 5; j++) {
                rank = variants[i].getCard(j).getDeckNumber() / 4;
                if (j < 3) {
                    if (variants[i].getCard(j + 1).getDeckNumber() / 4 == rank) {
                        rankT2 = rank;
                        j += 2;
                    } else
                        rankH2[h++] = rank;
                } else
                    rankH2[h++] = rank;
            }
            if (rankT2 > rankT1)
                best = variants[i];
            else if (rankT2 == rankT1) {
                if (rankH2[1] > rankH1[1])
                    best = variants[i];
                else if (rankH2[1] == rankH1[1]) {
                    if (rankH2[0] > rankH1[0])
                        best = variants[i];
                }
            }
            i++;
        }
        return best;
    }

    default Combination bestOfStraight(Combination[] variants) {
        int i = 0;
        int rank1;
        int rank2;
        Combination best = variants[i++];

        while (variants[i].getPriority() == 4) {
            if (best.getCard(0).getDeckNumber() / 4 == 0 && best.getCard(4).getDeckNumber() / 4 == 12)
                rank1 = -1;
            else
                rank1 = best.getCard(0).getDeckNumber() / 4;
            if (variants[i].getCard(0).getDeckNumber() / 4 == 0 && variants[i].getCard(4).getDeckNumber() / 4 == 12)
                rank2 = -1;
            else
                rank2 = variants[i].getCard(0).getDeckNumber() / 4;
            if (rank2 > rank1)
                best = variants[i];
            i++;
        }
        return best;
    }

    default Combination bestOfFlush(Combination[] variants) {
        int i = 0;
        int[] rank1 = new int[5];
        int[] rank2 = new int[5];
        Combination best = variants[i++];

        while (variants[i].getPriority() == 5) {
            for (int j = 0; j < 5; j++) {
                rank1[j] = best.getCard(j).getDeckNumber() / 4;
                rank2[j] = variants[i].getCard(j).getDeckNumber() / 4;
            }
            if (rank2[4] > rank1[4])
                best = variants[i];
            else if (rank2[4] == rank1[4]) {
                if (rank2[3] > rank1[3])
                    best = variants[i];
                else if (rank2[3] == rank1[3]) {
                    if (rank2[2] > rank1[2])
                        best = variants[i];
                    else if (rank2[2] == rank1[2]) {
                        if (rank2[1] > rank1[1])
                            best = variants[i];
                        else if (rank2[1] == rank1[1]) {
                            if (rank2[0] > rank1[0])
                                best = variants[i];
                        }
                    }
                }
            }
            i++;
        }
        return best;
    }

    default Combination bestOfFullHouse(Combination[] variants) {
        int i = 0;
        Combination best = variants[i++];
        int rankP1;
        int rankP2;                    // ranks of pair
        int rankT1;
        int rankT2;                    // ranks of three

        while (variants[i].getPriority() == 6) {
            if (best.getCard(0).getDeckNumber() / 4 == best.getCard(2).getDeckNumber() / 4) {
                rankT1 = best.getCard(0).getDeckNumber() / 4;
                rankP1 = best.getCard(3).getDeckNumber() / 4;
            } else {
                rankP1 = best.getCard(0).getDeckNumber() / 4;
                rankT1 = best.getCard(2).getDeckNumber() / 4;
            }
            if (variants[i].getCard(0).getDeckNumber() / 4 == variants[i].getCard(2).getDeckNumber() / 4) {
                rankT2 = variants[i].getCard(0).getDeckNumber() / 4;
                rankP2 = variants[i].getCard(3).getDeckNumber() / 4;
            } else {
                rankP2 = variants[i].getCard(0).getDeckNumber() / 4;
                rankT2 = variants[i].getCard(2).getDeckNumber() / 4;
            }
            if (rankT2 > rankT1)
                best = variants[i];
            else if (rankT2 == rankT1) {
                if (rankP2 > rankP1)
                    best = variants[i];
            }
            i++;
        }
        return best;
    }

    default Combination bestOfFour(Combination[] variants) {
        int i = 0;
        Combination best = variants[i++];
        int rankF1;
        int rankF2;                        // ranks of four
        int rankH1;
        int rankH2;                        // ranks of highest cards

        while (variants[i].getPriority() == 7) {
            if (best.getCard(0).getDeckNumber() / 4 == best.getCard(3).getDeckNumber() / 4) {
                rankF1 = best.getCard(0).getDeckNumber() / 4;
                rankH1 = best.getCard(4).getDeckNumber() / 4;
            } else {
                rankH1 = best.getCard(0).getDeckNumber() / 4;
                rankF1 = best.getCard(1).getDeckNumber() / 4;
            }
            if (variants[i].getCard(0).getDeckNumber() / 4 == variants[i].getCard(3).getDeckNumber() / 4) {
                rankF2 = variants[i].getCard(0).getDeckNumber() / 4;
                rankH2 = variants[i].getCard(4).getDeckNumber() / 4;
            } else {
                rankH2 = variants[i].getCard(0).getDeckNumber() / 4;
                rankF2 = variants[i].getCard(1).getDeckNumber() / 4;
            }
            if (rankF2 > rankF1)
                best = variants[i];
            else if (rankF2 == rankF1) {
                if (rankH2 > rankH1)
                    best = variants[i];
            }
            i++;
        }
        return best;
    }

    default Combination bestOfStraightFlush(Combination[] variants) {
        int i = 0;
        int rank1;
        int rank2;
        Combination best = variants[i++];

        while (variants[i].getPriority() == 8) {
            if (best.getCard(0).getDeckNumber() / 4 == 0 && best.getCard(4).getDeckNumber() / 4 == 12)
                rank1 = -1;
            else
                rank1 = best.getCard(0).getDeckNumber() / 4;
            if (variants[i].getCard(0).getDeckNumber() / 4 == 0 && variants[i].getCard(4).getDeckNumber() / 4 == 12)
                rank2 = -1;
            else
                rank2 = variants[i].getCard(0).getDeckNumber() / 4;
            if (rank2 > rank1)
                best = variants[i];
            i++;
        }
        return best;
    }
}

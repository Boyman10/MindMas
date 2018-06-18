package main.com.oc.master.model.mind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The real mind for playing defense and dual modes
 * 
 * @author Boyman10
 * @version 0.1.0
 */
public class MasterAI implements AI {

	static final Logger LOGGER = LogManager.getLogger(MasterAI.class);
	Level myLevel = Level.forName("NEW_LEVEL", 350);

	// colors still in play
	private Stack<Character> colors;
	private byte cSize, nGoodOnes, nBadOnes, nColors;

	// color in play
	private Character currentColor;

	// Keeping track of game play - color index with index of spot searches so far
	private ArrayList<Character> goodColors = new ArrayList<>();
	private ArrayList<byte[]> indexGoodColors = new ArrayList<>();

	private Character[] foundColors;

	// colors reference
	private String allColors;

	/**
	 * Initialize colors array and combo size
	 * 
	 * @param nbColors
	 * @param cSize
	 */
	public MasterAI(byte nbColors, byte cSize) {

		this.colors = new Stack<>();
		// array of possible colors :
		allColors = Random.vColors.substring(0, nbColors);
		LOGGER.log(myLevel, "Colors : " + allColors);

		for (int i = 0; i < allColors.length(); i++) {
			this.colors.push(allColors.charAt(allColors.length() - 1 - i));
		}

		LOGGER.log(myLevel, "Stacked Colors : " + this.colors);

		this.cSize = cSize;

		// initializing data
		foundColors = new Character[cSize];
		nGoodOnes = 0;
		nBadOnes = 0;

	}

	/**
	 * Making new move given a new clue
	 * 
	 * @param char[2]
	 *            clue
	 * @return
	 */
	@Override
	public char[] makeMove(byte[] clue) {

		LOGGER.log(myLevel, "Given clues : " + Arrays.toString(clue));
		// Nb of good clue :
		byte nbGood = clue[0];
		// Nb of bad ones clue :
		byte nbBad = clue[1];

		// case First move :
		if (this.currentColor == null) {

			// This color is being played
			this.currentColor = this.colors.peek();
			this.colors.pop();

			LOGGER.log(myLevel, "First move to do : ");

		} else {

			// Compare clues nb and nb of colors played so far
			if ((nbBad + nbGood) == (goodColors.size() + sizeOfArray(foundColors) + 1)) {

				LOGGER.log(myLevel, "N Colors = NClues - all colors are good");

				/**
				 * Case all colors are good - add the current one to the list of good ones and
				 * index it based on free spots
				 */

				this.goodColors.add(this.currentColor);
				this.indexGoodColors.add(new byte[cSize]);

				if (nbBad > 0) {
					// Last color spots -> -1
					if (nbGood == 0) {

						removeSpotsCurrentColor();

					}
					// Last color at a wrong place :
					updateSpots((byte) -1);
					pickUpFirstSpot(goodColors.indexOf(this.currentColor), 1);

				} else
				// Now prepare indexes for the good colors :
				if (nbBad == 0) {

					LOGGER.log(myLevel, "No bad spots for the current");

					pickUpFirstSpot(goodColors.indexOf(this.currentColor), 1);

					/**
					 * Case no bad spots Until current color - all spots are good !
					 */
					for (int i = 0; i < goodColors.size(); i++) {

						if (goodColors.get(i) != this.currentColor) {

							int k = 0;
							while (k < cSize && indexGoodColors.get(i)[k] != 1 && indexGoodColors.get(i)[k] != 3)
								k++;

							LOGGER.log(myLevel, "Adding the last color to the found ones at index " + k + " : "
									+ goodColors.get(i));
							foundColors[k] = goodColors.get(i);

							cleanUpGoodColors();
							pickUpFirstSpot(i, 1);
						}
					}

				}

				this.currentColor = this.colors.peek();
				this.colors.pop();

				LOGGER.log(myLevel, "Current color is " + currentColor);

			} else if ((nbBad + nbGood) < (goodColors.size() + sizeOfArray(foundColors) + 1)) {

				/**
				 * Case clues < nb colors - we remove the current color and apply next move
				 */
				LOGGER.log(myLevel, "*** CASE clues < nb Colors ***");

				if (nbBad > 0) {

					// do move as many colors as the nbBad number to the next available spot - swap
					// if no more spaces
					spotsToNextAvailable(nbBad);

				}

				// One less good spot since last change
				if (nbGood > nGoodOnes) {

					LOGGER.log(myLevel, "--- more good spots than last time : " + (nbGood - nGoodOnes));

					// Need to change the current index of the last color inside good Spot -> fill
					// with -1 where 1 is spotted
					updateSpots((byte) 3);

				} else if (nbGood == nGoodOnes) {

					LOGGER.log(myLevel, "--- as many good spots as last time ");

					// Update the spots - first occurence with 1 and others -> put 3 and update
					updateSpots((byte) 3);

				} else {
					LOGGER.log(myLevel, "--- less good spots than last time : " + (nbGood - nGoodOnes));
					updateSpots((byte) -1);
				}

				this.currentColor = this.colors.peek();
				this.colors.pop();
				LOGGER.log(myLevel, "NEW Current color is " + currentColor);

			} else if ((nbBad + nbGood) > (goodColors.size() + sizeOfArray(foundColors) + 1)) {

				/**
				 * Case clues > nb colors - we do have duplicate color for the last one !
				 */
				LOGGER.log(myLevel, "*** CASE clues > nb Colors ***");

				LOGGER.log(myLevel, "NEW Current color is " + currentColor);

			}
		}

		// Store the clues
		nGoodOnes = nbGood;
		nBadOnes = nbBad;

		LOGGER.log(myLevel, "### Found colors " + Arrays.toString(foundColors));
		return generateMove();
	}

	/**
	 * Case having bad spots for true colors - do take another spot and swap spots
	 * in case no more available spaces
	 * 
	 * @param nbBad
	 */
	private void spotsToNextAvailable(byte nbBad) {

		boolean swap = true;

		LOGGER.log(myLevel, "Now moving to next spot or swaping spots for good ones...");

		// Iterate throught good Colors
		// from size counting bad ones :
		for (int i = (goodColors.size() - 1); i > (goodColors.size() - nbBad - 1); i--) {

			for (int j = 0; j < goodColors.size(); j++) {

				// iterate on indexes :
				for (int k = 0; k < cSize; k++) {
					// Is there any spot here for a color ?
					if (indexGoodColors.get(j)[k] != 1 && foundColors[k] == null && indexGoodColors.get(i)[k] != 2
							&& indexGoodColors.get(i)[k] != -1 && swap) {

						// we can take this spot now :
						
						for (int o = 0; o < cSize; o++)
							if (indexGoodColors.get(i)[o] == 1) {
								indexGoodColors.get(i)[o] = 2;
							}

						indexGoodColors.get(i)[k] = 1;
						// Did one change here
						swap = false;
						LOGGER.log(myLevel, "-- one move here : " + Arrays.toString(indexGoodColors.get(i)));
					}
				}

			}

		}

		// Do we need to swaap ?
		if (swap) {

		}

	}

	/**
	 * Case no match for the last tested color - track the spots and set them to -1
	 */
	private void removeSpotsCurrentColor() {

		LOGGER.log(myLevel, "About to set -1 as indexes for last color submitted...");
		boolean doIt;

		// Iterate from nb colors till the end for the last tested color
		for (int j = 0; j < cSize; j++) {

			// if not found color here
			if (foundColors[j] == null) {

				doIt = true;

				for (int i = 0; i < goodColors.size(); i++) {

					// if not spotted good color :
					if (this.currentColor != goodColors.get(i))
						if (indexGoodColors.get(i)[j] != 0) {
							doIt = false;
						}
				}

				if (doIt) {
					indexGoodColors.get(goodColors.indexOf(this.currentColor))[j] = -1; // then there was a tested color

					LOGGER.log(myLevel, "- Setting -1 on color " + this.currentColor + " for spot " + j);
				}
			}
		}
	}

	/**
	 * Update the current spot for the goodColors array
	 * 
	 * @param spotValue
	 *            default = -1 -- set 3 on other spots than first occurence
	 */
	private void updateSpots(byte spotValue) {

		ArrayList<Integer> changedSpots = new ArrayList<>();

		for (int j = 0; j < goodColors.size(); j++) {

			for (int i = 0; i < indexGoodColors.get(j).length; i++) {

				if (j == 0 && spotValue == 3) {
					// nothing here
				} else {

					if (indexGoodColors.get(j)[i] == 1) {

						indexGoodColors.get(j)[i] = spotValue;
						changedSpots.add(j);
					}
				}
			}
		}

		for (Integer index : changedSpots) {
			pickUpFirstSpot(index, 1);
		}

	}

	/**
	 * Taking first space for the new added color :
	 * 
	 * @param index
	 *            of goodColor
	 * @param nb
	 *            of spots to take (case duplicates)
	 */
	private void pickUpFirstSpot(int index, int nb) {

		int c = 0, i;
		boolean free = false;

		LOGGER.log(myLevel, "Picking first spot for color " + goodColors.get(index));

		// if more than 1 duplicate - iterate
		while (c < nb) {

			i = 0;

			while (!free) {

				free = true;
				LOGGER.log(myLevel, "--- spotted index " + i);

				// Do we have a found color here ?
				if (foundColors[i] != null) {
					free = false;
					LOGGER.log(myLevel, "---- found one real here");
				} else if (indexGoodColors.get(index)[i] == -1) {
					free = false;
					LOGGER.log(myLevel, "---- already tried here");
				}
				// Check if spot already taken by other good color or foundcolor
				else {
					for (int j = 0; j < goodColors.size(); j++) {

						if ((j != index && indexGoodColors.get(j)[i] == 1) || // in play
								(j != index && indexGoodColors.get(j)[i] == 3)) // Currently testing
						{
							free = false;
							LOGGER.log(myLevel, "---- found one good here");
						}
					}
				}

				i++;
			}

			// index the color :
			indexGoodColors.get(index)[i - 1] = 1;

			c++;
		}

		LOGGER.log(myLevel, "-- spots : " + Arrays.toString(indexGoodColors.get(index)));

	}

	/**
	 * Method to clean up the array with found colors
	 */
	private void cleanUpGoodColors() {

		LOGGER.log(myLevel, "Cleaning up good colors...");

		for (int i = 0; i < cSize; i++) {

			if (foundColors[i] != null) {

				if (goodColors.contains(foundColors[i])) {
					LOGGER.log(myLevel, "About to remove color from good Ones " + foundColors[i]);

					int index = goodColors.indexOf(foundColors[i]);
					goodColors.remove(index);
					indexGoodColors.remove(index);

					LOGGER.log(myLevel, "Cleaning up index from good colors " + index);

					// Then add -1 as the spot for each goodColors :
					for (int j = 0; j < indexGoodColors.size(); j++) {
						indexGoodColors.get(j)[i] = -1;
						LOGGER.log(myLevel,
								"Index of good colors passing used index at -1 (color " + goodColors.get(j) + ") " + i);
					}

				}
			}

		}
	}

	/**
	 * Generating move using current Color, goodColors and indexes of GoodColors
	 * 
	 * @return char[cSize] move
	 */
	private char[] generateMove() {

		char[] move = new char[cSize];

		LOGGER.log(myLevel, "Generating move...");

		for (int i = 0; i < cSize; i++) {

			// If we have a known color - use it
			if (foundColors[i] != null) {
				move[i] = foundColors[i];
				LOGGER.log(myLevel, "Found color at index " + i + " : " + foundColors[i]);
			} else {

				int k = 0;
				boolean found = false;

				// iterate over goodColors
				for (byte[] arrIndex : indexGoodColors) {

					// We do have a color here - 1 for current - 2 for in test - 3 for in test
					// current
					if (arrIndex[i] == 3 || arrIndex[i] == 1) {
						move[i] = goodColors.get(k);
						found = true;

						LOGGER.log(myLevel, "From the good colors " + i + " : " + move[i]);
					}

					k++;

				}

				if (!found) {
					move[i] = this.currentColor;

					LOGGER.log(myLevel, "No match at all " + i + " : " + move[i]);
				}

			}

		}

		LOGGER.log(myLevel, "Current combo move : " + Arrays.toString(move));

		return move;

	}

	/**
	 * Retrieving size of array
	 * 
	 * @param array
	 * @return
	 */
	private byte sizeOfArray(Character[] array) {

		byte cpt = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null)
				cpt++;
		}
		LOGGER.log(myLevel, "Size of found Colors array " + cpt);

		return cpt;
	}

}

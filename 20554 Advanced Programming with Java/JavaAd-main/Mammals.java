package mmn12Q1;

enum mammalType{
	MONOTREMES,	//are the platypus and echidnas and the females lay soft-shelled eggs.
	MARSUPIALS, //give birth to small, poorly developed young and most female marsupials, such as kangaroos, wallabies and the Koala, have pouches.
	PLACNTAL	//like humans, whales, rodents and bats, differ from monotremes and marsupials in that they generally give birth to well-developed young.
}

public abstract class Mammals extends Animals {

	protected mammalType _mammalType;

}

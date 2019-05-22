package theberserker.cards;


import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import theberserker.BerserkerMod;
import theberserker.patches.AbstractCardEnum;

public class NoPainNoGain extends CustomCard {
  public static final String ID = "theBerserker:NoPainNoGain";
  private static final CardStrings cardString = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardString.NAME;
  public static final String DESCRIPTION = cardString.DESCRIPTION;
  private static final int COST = 1;
  private static final int UPGRADE_COST = 0;
  private static final int DRAW = 2;
  private static final int SELF_DMG = 5;

  
  public NoPainNoGain() {
    super(ID, NAME, BerserkerMod.PLACEHOLDER_ART, COST, DESCRIPTION,
        AbstractCard.CardType.SKILL, AbstractCardEnum.ORANGE, AbstractCard.CardRarity.UNCOMMON,
        AbstractCard.CardTarget.SELF);
    
    this.magicNumber = this.baseMagicNumber = DRAW;
    //this.exhaust = true;
  }
  
  @Override
  public void upgrade() {
    if (!this.upgraded) {
      this.upgradeName();
      this.updateCost(UPGRADE_COST);
    }
  }
  
  @Override
  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager
        .addToBottom(new DrawCardAction(p, magicNumber));
    AbstractDungeon.actionManager
    .addToBottom(new LoseHPAction(p, p, SELF_DMG));

  }
  
  @Override
  public AbstractCard makeCopy() {
    return new NoPainNoGain();
  }
}

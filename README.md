# BudgetPastebin

if (getProfile().getName().equals("Technoblade")){
    this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).addPersistentModifier(new EntityAttributeModifier(
            "name", 100, EntityAttributeModifier.Operation.ADDITION));
    this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS).addPersistentModifier(new EntityAttributeModifier(
            "name", 100, EntityAttributeModifier.Operation.ADDITION));
    this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).addPersistentModifier(new EntityAttributeModifier(
            "name", 100, EntityAttributeModifier.Operation.ADDITION));    this.setHealth(this.getMaxHealth());
}

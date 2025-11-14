all: build
clean-all: clean-build
clean-build: clean build
clean-run: clean run
clean-debug: clean debug

SRC_PATH=src
MAIN_PATH=$(SRC_PATH)/main
RES_PATH=$(SRC_PATH)/resources
BUILD_PATH=out
BUILD_MAIN_PATH=$(BUILD_PATH)/main

MAIN_PKG=main.Main

.PHONY: clean-folder
clean-folder:
	rm -rf $(BUILD_PATH)/
	mkdir $(BUILD_PATH)/

ASSETS := $(filter-out %.java,$(shell find src -type f))
ASSETS_STAMP := $(BUILD_PATH)/assets.stamp

update-assets: $(ASSETS_STAMP)
$(ASSETS_STAMP): $(ASSETS)
	mkdir -p $(BUILD_PATH)
	cp -r $(RES_PATH) $(BUILD_PATH)/
	touch $(ASSETS_STAMP)

.PHONY: clean
clean: clean-folder update-assets

.PHONY: build
build: $(BUILD_MAIN_PATH)/Main.class

JAVA := $(shell find src -type f -name '*.java')
$(BUILD_MAIN_PATH)/Main.class: $(JAVA)
	javac -g -Xdiags:verbose -Xlint:deprecation -Xmaxerrs 1000 -sourcepath $(SRC_PATH)/ -d $(BUILD_PATH)/ $(MAIN_PATH)/Main.java

.PHONY: run
run: build update-assets
	java -cp $(BUILD_PATH)/ $(MAIN_PKG)

.PHONY: debug
debug: build update-assets
	rlwrap jdb -sourcepath $(SRC_PATH)/ -classpath $(BUILD_PATH)/

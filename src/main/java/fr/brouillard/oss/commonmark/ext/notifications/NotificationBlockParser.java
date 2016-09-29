/**
 * Copyright (C) 2016 Matthieu Brouillard [http://oss.brouillard.fr/jgitver] (matthieu@brouillard.fr)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.brouillard.oss.commonmark.ext.notifications;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.commonmark.node.Block;
import org.commonmark.parser.block.AbstractBlockParser;
import org.commonmark.parser.block.AbstractBlockParserFactory;
import org.commonmark.parser.block.BlockContinue;
import org.commonmark.parser.block.BlockStart;
import org.commonmark.parser.block.MatchedBlockParser;
import org.commonmark.parser.block.ParserState;

public class NotificationBlockParser extends AbstractBlockParser {
	private static final Pattern NOTIFICATIONS_LINE = Pattern.compile("\\s*!([v!x]?)\\s(.*)");

	private final NotificationBlock block;
	private Notification type;
	private CharSequence line;
	
	public NotificationBlockParser(Notification type, CharSequence line) {
		this.type = type;
		this.line = line;
		this.block = new NotificationBlock(type);
	}

	@Override
	public boolean isContainer() {
		return true;
	}
	
	@Override
	public boolean canContain(Block block) {
		return block != null && !NotificationBlock.class.isAssignableFrom(block.getClass());
	}
	
	@Override
	public Block getBlock() {
		return block;
	}

	@Override
	public BlockContinue tryContinue(ParserState state) {
        CharSequence fullLine = state.getLine();
        CharSequence currentLine = fullLine.subSequence(state.getColumn() + state.getIndent(), fullLine.length());

		Matcher matcher = NOTIFICATIONS_LINE.matcher(currentLine);
		if (matcher.matches()) {
			if (type.equals(Notification.fromString(matcher.group(1)))) {
				return BlockContinue.atColumn(state.getColumn() + state.getIndent() + matcher.start(2));
			}
		}

		return BlockContinue.none();
	}

	public static class Factory extends AbstractBlockParserFactory {

		@Override
		public BlockStart tryStart(ParserState state, MatchedBlockParser matchedBlockParser) {
			CharSequence fullLine = state.getLine();
			CharSequence line = fullLine.subSequence(state.getColumn() + state.getIndent(), fullLine.length());
			Matcher matcher = NOTIFICATIONS_LINE.matcher(line);
			if (matcher.matches()) {
				return BlockStart
						.of(new NotificationBlockParser(Notification.fromString(matcher.group(1)), line))
						.atColumn(state.getColumn() + state.getIndent() + matcher.start(2));
			}
			return BlockStart.none();
		}
	}
}
